package com.example.ch13_provider

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.ch13_provider.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var filePath: String//camera app....

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val galleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            try{
                //OOM 고려해서.. 줄여서 로딩... 작업 옵션..
                val option = BitmapFactory.Options()
                option.inSampleSize = 10
                //gallery app 에서 넘어오는 것은... 선택한 사진의 식별자.. url 형식으로..
                //gallery app 에서 파일 경로를 얻을 수도 있고.. 그 파일을 읽을 수 있는 inputStream 도 제공..
                val inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                bitmap?.let {
                    //not null
                    binding.userImageView.setImageBitmap(bitmap)
                } ?: let {
                    //null 이면..
                    Log.d("kkang", "bitmap null....")
                }
            }catch (e: java.lang.Exception){
                e.printStackTrace()
            }
        }
        binding.galleryButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }

        val cameraLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            val option = BitmapFactory.Options()
            option.inSampleSize = 10
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        binding.cameraButton.setOnClickListener {
            //파일 준비..
            val timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            //외장 - 앱별 - 사진..
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath
            //camera app 에 넘길 정보..
            val uri = FileProvider.getUriForFile(
                this, "com.example.ch13_provider.fileprovider", file
            )
            //camera app 연동..
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            cameraLauncher.launch(intent)
        }
    }
}