package org.d3if4119.praassessment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*
import kotlinx.android.synthetic.main.fragment_segitiga.*


class persegiPanjangFragment : Fragment() {
    private lateinit var binding: persegiPanjangFragment
    private var luas = 0.0
    private var keliling = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_persegi_panjang, container, false)

        binding.btnHitungPersegiPanjang.setOnClickListener { checkInput() }
        binding.btnSharePersegiPanjang.setOnClickListener { sendEmail() }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun judul() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = "Persegi Panjang"
    }

    private fun checkInput() {
        when {
            binding.hintPanjang.text.isEmpty() -> {
                Toast.makeText(activity, "Panjang harus diisi!", Toast.LENGTH_SHORT).show()
            }
            binding.hintLebar.text.isEmpty() -> {
                Toast.makeText(activity, "Lebar harus diisi!", Toast.LENGTH_SHORT).show()
            }
            else -> hitungHasil()
        }
    }

    private fun visibleResult() {
        binding.tKelilingS.visibility = View.VISIBLE
        binding.tLuasS.visibility = View.VISIBLE
        binding.tKelilingPP.visibility = View.VISIBLE
        binding.tLuasPP.visibility = View.VISIBLE
        binding.btnHitungPersegiPanjang.visibility = View.VISIBLE
        binding.btnSharePersegiPanjang.visibility = View.VISIBLE
    }

    private fun hitungHasil() {
        var panjang = binding.hintPanjang.text.toString().toDouble()
        var lebar = binding.hintLebar.text.toString().toDouble()
        luas = panjang * lebar
        keliling = 2 * (panjang + lebar)

        // visible result
        visibleResult()

        // inject result to text view
        binding.tLuasPP.text = "${luas}"
        binding.tKelilingPP.text = "${keliling}"
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        val subject = "Hasil Pehitungan Persegi Panjang"
        val message = """
            Panjang = ${binding.hintPanjang.text}
            Lebar = ${binding.hintLebar.text}
            Luas = ${binding.tLuasPP.text}
            Keliling = ${binding.tKelilingPP.text}
        """.trimIndent()
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("luas", luas)
        outState.putDouble("keliling", keliling)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            luas = savedInstanceState.getDouble("luas")
            keliling = savedInstanceState.getDouble("keliling")

            // visible result on rotate
            visibleResult()

            // inject result to text view
            binding.tLuasPP.text = "${luas}"
            binding.tKelilingPP.text = "${keliling}"
        }
        super.onViewStateRestored(savedInstanceState)
    }

}
