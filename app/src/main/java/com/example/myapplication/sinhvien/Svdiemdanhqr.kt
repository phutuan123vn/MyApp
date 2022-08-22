package com.example.myapplication.sinhvien

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.budiyev.android.codescanner.*
import com.example.myapplication.R
import com.example.myapplication.login.LoginSV
import com.example.myapplication.model.TEMP
import kotlinx.android.synthetic.main.svdiemdanhqr.*
import kotlinx.android.synthetic.main.svdiemdanhqr.view.*
import kotlinx.coroutines.delay

private const val CAMERA_REQUEST_CODE = 101
class Svdiemdanhqr : Fragment() {
    private lateinit var codeScanner: CodeScanner
    private lateinit var svinfodata :Svinfodata

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.svdiemdanhqr, container, false)
        svinfodata = activity as Svinfodata
        val back = v.findViewById<Button>(R.id.quetmaqrback)
        back.setOnClickListener {
            val Svdiemdanh = Svdiemdanh()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right,R.anim.slide_in_right,R.anim.slide_out_left)
            transaction.replace(R.id.framelayoutsvnav, Svdiemdanh)
            transaction.commit()
        }
        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPermissons()
        val scannerView = view.findViewById<CodeScannerView>(R.id.camerascan)
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false
        }
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                val temp = TEMP()
                temp.t9 = it.text
                svinfodata.passData1(temp)
            }
            val bundle = Bundle()
            val svdiemdanh = Svdiemdanh()
            svdiemdanh.arguments = bundle
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right,R.anim.slide_in_right,R.anim.slide_out_left)
            transaction.replace(R.id.framelayoutsvnav, svdiemdanh)
            transaction.commit()
        }
        scannerView.setOnClickListener {
            camerascantext.setText("Scan somthing...")
            setupPermissons()
            codeScanner.startPreview()
        }
    }
    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
    private fun setupPermissons(){
        val permission = activity?.let { ContextCompat.checkSelfPermission(it,android.Manifest.permission.CAMERA) }
        if(permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest() {
        activity?.let { ActivityCompat.requestPermissions(it, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE) }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            CAMERA_REQUEST_CODE ->{
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(activity, "You need camera to use this app!",Toast.LENGTH_SHORT).show()
                }else{
                    //sucessful
                }
            }
        }
    }

}