package com.jeongdaeri.mycustomdialog_tutorial

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.custom_dialog.*

class MyCustomDialog(context: Context,
                     myCustomDialogInterface: MyCustomDialogInterface)
                    : Dialog(context), View.OnClickListener
{

    val TAG: String = "로그"

    //
    private var myCustomDialogInterface: MyCustomDialogInterface? = null

//    var subscribeAction : (() -> Unit)? = null
    var subscribeAction : ((String) -> Unit)? = null
    var likeAction : (() -> Unit)? = null

    // 인터페이스 연결
    init {
        this.myCustomDialogInterface = myCustomDialogInterface
    }

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.custom_dialog)

        Log.d(TAG, "MyCustomDialog - onCreate() called")
        // 배경 투명
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        like_btn.setOnClickListener(this)
        subscribe_btn.setOnClickListener(this)


    }

    override fun onClick(view: View?) {
        when(view){

            // 좋아요 버튼이 클릭 되었을때
            like_btn -> {
                Log.d(TAG, "MyCustomDialog - 좋아요 버튼 클릭!")

                this.likeAction?.invoke()

//                if (this.likeAction != null) {
//                    this.likeAction.invoke()
//                }
//
//                this.likeAction?.let { it() }

                this.myCustomDialogInterface?.onLikeBtnClicked()
            }

            // 구독 버튼이 클릭 되었을때
            subscribe_btn -> {
                Log.d(TAG, "MyCustomDialog - 구독 버튼 클릭!")

                val userInput = user_input.text.toString()

                this.subscribeAction?.invoke(userInput)

                this.myCustomDialogInterface?.onSubscribeBtnClicked()
            }
        }
    }


}
