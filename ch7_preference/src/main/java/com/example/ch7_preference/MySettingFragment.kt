package com.example.ch7_preference

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

//설정 자동화 클래스...
//PreferenceFragmentCompat 을 상속받았는데... Fragment 이다.. View 이다..
//이 클래스를 activity 에서 화면에 띄워야 한다..
class MySettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //아래의 코드 한줄만으로도.. 화면, 설정내용 저장까지 자동화..
        setPreferencesFromResource(R.xml.settings, rootKey)

        //설정 객체 획득..
        val idPreference: EditTextPreference? = findPreference("id")
        val colorPreference: ListPreference? = findPreference("color")

        //summary 동적 지정... 유저 설정 값을 그대로 summary 로 지정하겠다면..
        colorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
        //summary 동적 지정.. 우리 알고리즘으로..
        idPreference?.summaryProvider = Preference.SummaryProvider<EditTextPreference>{
            //설정된 값을 획득하고..
            val text = it.text
            if(TextUtils.isEmpty(text)){
                "ID 를 설정해 주세요."
            }else {
                "설정하신 ID 는 $text 입니다."
            }
        }

        idPreference?.setOnPreferenceChangeListener{ preference, newValue ->
            Log.d("kkang", "id change : ${preference.key}, newValue : $newValue")
            true
        }
    }
}







