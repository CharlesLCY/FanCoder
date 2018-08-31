package com.lcy.fancoder.utils

import android.content.Context
import android.text.TextUtils
import com.lcy.fancoder.C
import com.lcy.fancoder.FanApplication

/**
 * @author FanCoder.LCY
 * @date   2018/8/31 11:24
 * @email  15708478830@163.com
 * @desc
 **/
class SharePrefUtil {
    companion object {
        private fun setMySP(context: Context, fileName: String, key: String,
                            value: Any) {
            val sp = context.getSharedPreferences(fileName,
                    Context.MODE_PRIVATE)
            val editor = sp.edit()
            if (value is Boolean) {
                editor.putBoolean(key, value)
            } else if (value is Float) {
                editor.putFloat(key, value)
            } else if (value is Int) {
                editor.putInt(key, value)
            } else if (value is Long) {
                editor.putLong(key, value)
            } else if (value is String) {
                editor.putString(key, value)
            }
            editor.apply()
        }

        private fun getMySP(context: Context, fileName: String, key: String,
                            clazz: Class<*>, defValue: Any?): Any? {
            if (defValue == null) {
                return null
            }

            var `object`: Any? = null
            val sp = context.getSharedPreferences(fileName,
                    Context.MODE_PRIVATE)
            val name = clazz.name.substring(10)
            if ("Boolean" == name) {
                var def = false
                if (defValue is Boolean) {
                    def = (defValue as Boolean?)!!
                }
                `object` = sp.getBoolean(key, def)
            } else if ("Float" == name) {
                `object` = sp.getFloat(key,
                        defValue as? Float ?: 0f)
            } else if ("Integer" == name) {
                `object` = sp.getInt(key,
                        defValue as? Int ?: 0)
            } else if ("Long" == name) {
                `object` = sp.getLong(key, defValue as? Long ?: 0L)
            } else if ("String" == name) {
                var def: String? = null
                if (defValue is String) {
                    def = defValue
                }
                `object` = sp.getString(key, def)
            }
            return `object`
        }

        /**
         * 清空sp
         */
        fun delMySp(context: Context, fileName: String) {
            if (!TextUtils.isEmpty(fileName)) {
                val sp = context.getSharedPreferences(fileName,
                        Context.MODE_PRIVATE)
                sp.edit().clear().apply()
            }
        }

        /**
         * 清除指定键值的sp
         * @param key
         */
        fun delSpByKey(key: String) {
            val sp = FanApplication.getInstance().getSharedPreferences(C.Global.PREFERENCE_FILE_NAME,
                    Context.MODE_PRIVATE)
            sp.edit().remove(key).apply()
        }

        fun setSP(key: String, value: Any) {
            setMySP(FanApplication.getInstance(), C.Global.PREFERENCE_FILE_NAME, key, value)
        }

        fun getSP(key: String, clazz: Class<*>, value: Any): Any? {
            return getMySP(FanApplication.getInstance(), C.Global.PREFERENCE_FILE_NAME, key, clazz, value)
        }
    }
}