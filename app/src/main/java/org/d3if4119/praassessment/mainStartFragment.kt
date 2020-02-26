package org.d3if4119.praassessment


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.fragment_main_start.*
import org.d3if4119.praassessment.databinding.FragmentMainStartBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class mainStartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        val binding: mainStartFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_main_start, container, false)
        binding.btnPersegiPanjang.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainStartFragment_to_persegiPanjangFragment)
        }
        binding.btnSegitiga.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainStartFragment_to_segitigaFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun judul() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = "Home"
    }


}
