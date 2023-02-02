package ph.kodego.victor.yu.leones.patricia.ivee.saving_images_module

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView

class TouchEventView (context:Context,attrs: AttributeSet):AppCompatImageView(context, attrs) {

    companion object {
        private val path = Path()
        private var localContext:Context? = null

    }

    private val paint = Paint()
    private  var gestureDetector: GestureDetector? = null
    init{
        localContext = context
        gestureDetector =
            GestureDetector(context, GestureListerner())

        paint.isAntiAlias = true
        paint.strokeWidth  = 6f
        paint.color = Color.LTGRAY

        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
    }

    private class GestureListerner: GestureDetector.SimpleOnGestureListener(){
        override fun onDoubleTap(e:MotionEvent): Boolean {
            val x = e.x
            val y = e.y
            path.reset()
            Toast.makeText(localContext,"Double Tap >> Tapped at:($x,$y)",Toast.LENGTH_SHORT).show()
            return true
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val eventX = event.x
        val eventY = event.y

        when(event.action) {
            MotionEvent.ACTION_DOWN ->{
                path.moveTo(eventX, eventY)
                return true
            }
            MotionEvent.ACTION_MOVE -> path.lineTo(eventX, eventY)
            MotionEvent.ACTION_UP ->{}
            else -> return false

        }
        gestureDetector!!.onTouchEvent(event)
        invalidate()
        return true
    }

}