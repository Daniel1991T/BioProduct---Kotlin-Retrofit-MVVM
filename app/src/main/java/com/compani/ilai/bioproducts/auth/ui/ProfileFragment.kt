package com.compani.ilai.bioproducts.auth.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.compani.ilai.bioproducts.R
import com.compani.ilai.bioproducts.data.entities.Producer
import com.compani.ilai.bioproducts.ui.MainActivity
import com.compani.ilai.bioproducts.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.ByteArrayOutputStream

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var viewModel: ProfileViewModel
    private val firebaseAuth = FirebaseAuth.getInstance()

    private lateinit var mContext: FragmentActivity

    private lateinit var producer: Producer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as FragmentActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)

        btn_update.setOnClickListener {
//            if (initUser()) {
//                setProducer(producer).addOnSuccessListener {
//                    FancyToast.makeText(
//                        context,
//                        "Update successful",
//                        FancyToast.LENGTH_SHORT,
//                        FancyToast.SUCCESS,
//                        false
//                    ).show()
//                    startActivity(Intent(requireContext(), MainActivity::class.java))
//                }
//                    .addOnFailureListener {
//                        FancyToast.makeText(
//                            context,
//                            "Something wrong",
//                            FancyToast.LENGTH_SHORT,
//                            FancyToast.ERROR,
//                            false
//                        ).show()
//
//                    }
//            } else {
//                FancyToast.makeText(requireContext(), "Field empty", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false)
//            }

            if (initUser()) {
                viewModel.saveProducer(producer)
                viewModel.producerUser.observe(viewLifecycleOwner) { result ->
                    when (result.status) {
                        Resource.Status.SUCCESS -> {
                            FancyToast.makeText(
                                requireContext(),
                                "Update successful",
                                FancyToast.LENGTH_SHORT,
                                FancyToast.SUCCESS,
                                false
                            ).show()
                            startActivity(Intent(requireContext(), MainActivity::class.java))
                        }
                        Resource.Status.ERROR -> {
                            FancyToast.makeText(
                                requireContext(),
                                "Something wrong",
                                FancyToast.LENGTH_SHORT,
                                FancyToast.ERROR,
                                false
                            ).show()
                        }
                        else -> Unit
                    }
                }
            } else {
                FancyToast.makeText(
                    requireContext(),
                    "Field empty",
                    FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR,
                    false
                ).show()
            }
        }
    }

//    private fun setProducer(producer: Producer): Task<Void> {
//            return viewModel.saveProducer(producer)
//
//    }

    private fun initUser(): Boolean {
        val producerId = firebaseAuth.currentUser?.uid
        // TODO image assert
        val fullname = tv_set_profile_name.text.toString()
        val address = tv_set_address.text.toString()
        val phone: Int = tv_set_phone.inputType
        val shortBio = tv_set_bio.text.toString()
        val productType = tv_set_product_type.text.toString()

        if (producerId?.isEmpty()!!) {
            return false
        }
        if (fullname.isEmpty()) return false
        if (address.isEmpty()) return false
        if (phone == 0) return false
        if (shortBio.isEmpty()) return false
        if (productType.isEmpty()) return false


        producer =
            Producer(
                producerId = producerId,
                fullName = fullname,
                address = address,
                phone = phone,
                shortBio = shortBio,
                productType = productType,
                imageUrl = null
            )
        return true
    }

    private fun imageToBitmap(image: ImageView): ByteArray {
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
    }


}