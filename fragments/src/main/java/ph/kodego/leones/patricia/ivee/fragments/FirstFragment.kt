package ph.kodego.leones.patricia.ivee.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import ph.kodego.leones.patricia.ivee.fragments.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToSecond.setOnClickListener {

//            create a bundle to pass data from one fragment to another
//            arguments is modern way to pass data from one fragment to another
//            Basic Activity can includes fragments
            val bundle = bundleOf("data" to "SAMPLE DATA", "info" to "INFORMATION")
            findNavController().navigate(R.id.action_firstfragment_to_secondfragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}