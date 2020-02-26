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
import kotlinx.android.synthetic.main.fragment_segitiga.*
import java.lang.Math.sqrt
import java.text.DecimalFormat
import kotlin.math.pow

class segitigaFragment : Fragment() {
    private lateinit var binding: segitigaFragment
    private var luas = 0.0
    private var keliling = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_segitiga, container, false)

        binding.btnHitungSegitiga.setOnClickListener { checkInput() }
        binding.btnShareSegitiga.setOnClickListener { sendEmail() }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun judul() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = "Segitiga"
    }

    private fun checkInput() {
        when {
            binding.hintAlas.text.isEmpty() -> {
                Toast.makeText(activity, "Alas harus diisi!", Toast.LENGTH_SHORT).show()
            }
            binding.hintTinggi.text.isEmpty() -> {
                Toast.makeText(activity, "Tinggi harus diisi!", Toast.LENGTH_SHORT).show()
            }
            else -> hitungHasil()
        }
    }

    private fun visibleResult() {
        binding.tKelilingS.visibility = View.VISIBLE
        binding.tLuasS.visibility = View.VISIBLE
        binding.tKelilingS.visibility = View.VISIBLE
        binding.tLuasS.visibility = View.VISIBLE
        binding.btnHitungSegitiga.visibility = View.VISIBLE
        binding.btnShareSegitiga.visibility = View.VISIBLE
    }

    private fun hitungHasil() {
        val df = DecimalFormat("#.##")
        val alas = binding.hintAlas.text.toString().toDouble()
        val tinggi = binding.hintTinggi.text.toString().toDouble()
        val sisiMiring = sqrt(alas.pow(2) + tinggi.pow(2))
        luas = (alas * tinggi) / 2
        keliling = alas + tinggi + sisiMiring

        // visible result
        visibleResult()

        // inject result to text view
        binding.tLuasS.text = df.format(luas).toString()
        binding.tKelilingS.text = df.format(keliling).toString()
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        val subject = "Hasil Perhitungan Segitiga"
        val message = """
            Alas = ${binding.hintAlas.text}
            Tinggi = ${binding.hintTinggi.text}
            Luas = ${binding.tLuasS.text}
            Keliling = ${binding.tKelilingS.text}
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
            val df = DecimalFormat("#.##")
            luas = savedInstanceState.getDouble("luas")
            keliling = savedInstanceState.getDouble("keliling")

            //visible result
            visibleResult()

            // inject result to text view
            binding.tLuasS.text = df.format(luas).toString()
            binding.tKelilingS.text = df.format(keliling).toString()
        }
        super.onViewStateRestored(savedInstanceState)
    }


}
