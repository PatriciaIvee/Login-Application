package ph.kodego.leones.patricia.ivee.save_images

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.graphics.*
import android.graphics.Bitmap.CompressFormat
import android.graphics.Bitmap.createBitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import java.io.*
import java.util.*
import kotlin.math.ceil


private const val STROKE_WIDTH = 12f

class TouchEventView (context:Context,attrs: AttributeSet):AppCompatImageView(context, attrs) {


    private val path = Path()
    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    private lateinit var extraCanvas:Canvas
    private lateinit var extraBitmap: Bitmap
    private lateinit var frame: Rect
    private var viewWidth: Int = 0
    private var viewHeight: Int = 0

    private val paint = Paint().apply{
        color = drawColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = STROKE_WIDTH
    }

    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop
    private var currentX = 0f
    private var currentY = 0f
    private var motionTouchEventX= 0f
    private var motionTouchEventY = 0f


    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = createBitmap(width,height,Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)
        viewHeight = height
        viewWidth = width

        val inset = 40
        frame = Rect(inset, inset, width - inset, height - inset)

    }

    override fun onDraw(canvas: Canvas){
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
        //Add Frame
//        extraCanvas.drawRect(frame, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when(event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
            else -> Log.i("EVENT", event.action.toString())

        }
        return true
    }

    private fun touchStart(){
        path.reset()
        path.moveTo(motionTouchEventX,motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove(){
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)
        if (dx >= touchTolerance || dy >= touchTolerance){
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX)/ 2, (motionTouchEventY +currentY)/2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY
            extraCanvas.drawPath(path, paint)
        }
        invalidate()
    }

    private fun touchUp(){
        path.reset()
    }

    fun loadImage(){
        val externalFolder = "${context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)}${File.separator}sample_images"
        var imageFile = File(externalFolder, "KODEGO_IMAGE-141231031.jpg")
        var newBitmap :Bitmap? = null

        var fileInputStream = FileInputStream(imageFile)
        val bmpFactoryOptions = BitmapFactory.Options()
        bmpFactoryOptions.inJustDecodeBounds = true

        val widthRatio =
            ceil((bmpFactoryOptions.outWidth / viewWidth).toDouble()).toInt()
        val heightRatio =
            ceil((bmpFactoryOptions.outHeight / viewHeight).toDouble()).toInt()

        if (heightRatio>widthRatio) {
            bmpFactoryOptions.inSampleSize = heightRatio
        } else{
            bmpFactoryOptions.inSampleSize = widthRatio
        }
        bmpFactoryOptions.inJustDecodeBounds = false
        newBitmap = BitmapFactory.decodeStream(fileInputStream, null, bmpFactoryOptions)

        path.reset()
        extraBitmap = createBitmap(newBitmap!!.width, newBitmap!!.height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawBitmap(newBitmap!!, 0f, 0f, paint)
        invalidate()
    }

    fun loadImage(uri: Uri){
        var newBitmap:Bitmap? = null

        val bmpFactoryOptions = BitmapFactory.Options()
        bmpFactoryOptions.inJustDecodeBounds = true

        val widthRatio =
            ceil((0 / viewWidth).toDouble()).toInt()
        val heightRatio =
            ceil((0 / viewHeight).toDouble()).toInt()
        if (heightRatio > widthRatio) {
            bmpFactoryOptions.inSampleSize = heightRatio
        } else {
            bmpFactoryOptions.inSampleSize = heightRatio
        }
        bmpFactoryOptions.inJustDecodeBounds = false
        newBitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri),
            null, bmpFactoryOptions)

        path.reset()

        extraBitmap = createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawBitmap(newBitmap!!, 0f,0f, paint)
        invalidate()

    }


    fun saveImage(){

        val date = Date()
        val fileInfo = "${date.month}${date.date}${date.year}${date.hours}${date.minutes}"


        val externalFolder = "${context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)}${File.separator}sample_images"

        val saveFile:File = File(externalFolder, "KODEGO_IMAGE-${fileInfo}.jpg")

        if(!File(externalFolder).exists()){
            File(externalFolder).mkdir()
            Log.i("FILES", "Directory created : ${externalFolder}")

        } else{
            Log.i("FILES", "${saveFile}")
        }

        try {
            val fileOutputStream = FileOutputStream(saveFile)

            extraBitmap.compress(CompressFormat.JPEG, 95, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e:Exception){
            Log.e("ERROR", "Saving File Error")
            Log.e("ERROR", e.message.toString())

        }

    }

   fun saveImageByMediaStore(name:String){
       val values = ContentValues().apply {
           put(MediaStore.MediaColumns.DISPLAY_NAME, name)
           put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
           put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)

       }

       val resolver = context.contentResolver
       var uri:Uri? = null

       try {
           uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)
               ?:throw IOException("Failed to create a MediaStore Record.")

           resolver.openOutputStream(uri)?.use{
               if (!extraBitmap.compress(CompressFormat.JPEG,95,it))
                   throw IOException("Failed save bitmap.")
           } ?: throw IOException ("Failed to open Output Stream.")
       } catch (e:IOException){
           uri?.let { orphanUri ->
               resolver.delete(orphanUri, null, null)

           }
                e.printStackTrace()
       }

   }


    @RequiresApi(Build.VERSION_CODES.O)
    fun saveImageInDatabase(name:String){
        val stream = ByteArrayOutputStream()
        extraBitmap.compress(CompressFormat.JPEG, 95, stream)
        val bytes = stream.toByteArray()
        var data = Base64.getEncoder().encodeToString(bytes)

        val values = ContentValues().apply {
            put(DatabaseHandler.TABLE_IMAGES_TEXT_NAME, name)
            put(DatabaseHandler.TABLE_IMAGES_TEXT_DATA, "image/jpeg")

        }

        var databaseHandler:DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.writableDatabase

        val success = db.insert(DatabaseHandler.TABLE_IMAGES_TEXT, null, values)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadImageInDatabase(name:String) {
        var imageData: String = ""

        val columns = arrayOf(DatabaseHandler.TABLE_IMAGES_TEXT_DATA,
            DatabaseHandler.TABLE_IMAGES_TEXT_NAME,
            DatabaseHandler.TABLE_IMAGES_TEXT_ID)


        var databaseHandler:DatabaseHandler =DatabaseHandler(context)
        val db = databaseHandler.readableDatabase
        var cursor: Cursor? = null

        try{
            cursor = db.query(DatabaseHandler.TABLE_IMAGES_TEXT,
            columns,
            "${DatabaseHandler.TABLE_IMAGES_TEXT_NAME} like '%${name}%",
            null,
            null,
            null,
            null)
        } catch (e:SQLiteException) {
            db.close()
        }

        if (cursor!!.moveToFirst()){
            do{
                imageData = cursor.getString(0)
            }while (cursor!!.moveToNext())
        }

        if (imageData.isNotEmpty()){
            var imageByte = Base64.getDecoder()!!.decode(imageData)
            var newBitmap:Bitmap? = null


            val bmpFactoryOptions = BitmapFactory.Options()
            bmpFactoryOptions.inJustDecodeBounds = true

            val widthRatio =
                ceil((0 / viewWidth).toDouble()).toInt()
            val heightRatio =
                ceil((0 / viewHeight).toDouble()).toInt()
            if (heightRatio > widthRatio) {
                bmpFactoryOptions.inSampleSize = heightRatio
            } else {
                bmpFactoryOptions.inSampleSize = heightRatio
            }
            bmpFactoryOptions.inJustDecodeBounds = false

            var byteArrayInputStream =  ByteArrayInputStream(imageByte)

            newBitmap = BitmapFactory.decodeStream(byteArrayInputStream, null, bmpFactoryOptions)
            path.reset()
            extraBitmap = createBitmap(newBitmap!!.width,newBitmap.height, Bitmap.Config.ARGB_8888)
            extraCanvas = Canvas(extraBitmap)
            extraCanvas.drawBitmap(newBitmap!!,0f,0f,paint)
        }


    }

}