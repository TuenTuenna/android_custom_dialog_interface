package com.jeongdaeri.mycustomdialog_tutorial

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), MyCustomDialogInterface {

    companion object {
        const val TAG: String = "로그"
    }

    // 고차함수
    // 콜렉션(배열, 어레이리스트, 맵, .. )

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "MainActivity - onCreate() called")

        someFunction()
        someLambda.invoke()

        someFunctionWithParam("하하하")
        someLambdaWithParam("호호호")

        someFunctionWithMultiParams("하하하", 10)
        someLambdaWithMultiParams("호호호", 10)

        val totalValue1 = someFuntionWithReturn(3, 5)
        Log.d(TAG, "onCreate - totalValue1: $totalValue1")

        Log.d(TAG, "onCreate - totalValue2: ${someLambdaWithReturn(6, 3)}")

//        this.someFunctionWithLambda {
//            Log.d(TAG, "onCreate: 3초가 지났습니다!")
//        }
//
//        this.someFunctionWithParamAndLambda(300, completion = {
//            Log.d(TAG, "onCreate: 받은 값 [ $it ]입니다 ")
//        })

        // 자료구조는 자료가 모여진거
        var someList = mutableListOf<Int>()
        someList.add(0)
        someList.add(1)
        someList.add(2)
        someList.add(3)
        someList.add(4)
        someList.add(5)
        someList.add(6)

        val transformedList = someList.map { number ->
            "하하 $number"
        }
        transformedList.forEach {
            Log.d(TAG, "onCreate: forEach $it")
        }
//        Log.d(TAG, "onCreate: transformedList : $transformedList")
        val myTestBtn = findViewById<Button>(R.id.test_btn)
//        myTestBtn.setOnClickListener()
        myTestBtn.setOnClickListener {
            it.setBackgroundColor(R.color.colorPrimary)
            Log.d(TAG, "onCreate: myTestBtn clicked")
        }
    }

    // 매개변수 X, 반환 X
    fun someFunction () {
        Log.d(TAG, "someFunction: () called")
    }

    fun someFunctionWithParam (title: String) {
        Log.d(TAG, "someFunctionWithParam: () called $title")
    }

    fun someFunctionWithMultiParams (title: String, number: Int) {
        Log.d(TAG, "someFunctionWithParam: () called $title, $number")
    }

    fun someFuntionWithReturn (firstNumber: Int, secondNumber: Int) : String {
        Log.d(TAG, "someFunction: () called")
        val total = firstNumber + secondNumber
        return "$firstNumber + $secondNumber 는 $total 입니다"
    }

    fun someFunctionWithLambda (completion: () -> Unit){
        Log.d(TAG, "someFunctionWithLambda: ")
        Handler().postDelayed({
            completion()
        }, 3000L)
    }

    fun someFunctionWithParamAndLambda (myNumber: Int, completion: (String) -> Unit){
        Log.d(TAG, "someFunctionWithParamAndLambda: ")
        Handler().postDelayed({
            completion("myNumber 는 $myNumber 입니다! 그리고 3초가 지났습니다!")
        }, 3000L)
    }

    // 매개변수 X, 반환 X
    val someLambda : () -> Unit = {
        Log.d(TAG, "someLambda() called")
    }

//    val someLambdaWithParam : (String) -> Unit = {
//        Log.d(TAG, "someLambda() called $it")
//    }

    val someLambdaWithParam : (String) -> Unit = { userInput ->
        Log.d(TAG, "someLambdaWithParam() called $userInput")
    }

    val someLambdaWithMultiParams : (String, Int) -> Unit = { userInput, number ->
        Log.d(TAG, "someLambdaWithParam() called $userInput, $number")
    }

    // Void
//    val someLambdaWithMultiParams = { userInput : String, number : Int ->
//        Log.d(TAG, "someLambdaWithParam() called $userInput, $number")
//    }

//    val someLambdaWithMultiParams : (String, Int) -> Unit = { userInput, _ ->
//        Log.d(TAG, "someLambdaWithParam() called $userInput")
//    }

    val someLambdaWithReturn : (Int, Int) -> String = { first, second ->
        Log.d(TAG, "someFunction: () called")
        val total = first + second
        "$first + $second 는 $total 입니다"
    }

    fun onDialogBtnClicked(view: View){
        Log.d(TAG, "MainActivity - onDialogBtnClicked() called")

        val myCustomDialog = MyCustomDialog(this, this)

        myCustomDialog.likeAction = {
            Log.d(TAG, "onDialogBtnClicked: likeAction() called")
        }

        myCustomDialog.subscribeAction = { userInput ->
            Log.d(TAG, "onDialogBtnClicked: subscribeAction() called $userInput")
        }

        myCustomDialog.show()

    }


    // 구독버튼 클릭
    override fun onSubscribeBtnClicked() {
        Log.d(TAG, "MainActivity - onSubscribeBtnClicked() called")
        Toast.makeText(this, "구독버튼 클릭!", Toast.LENGTH_SHORT).show()
    }

    // 좋아요 버튼 클릭
    override fun onLikeBtnClicked() {
        Log.d(TAG, "MainActivity - onLikeBtnClicked() called")
        Toast.makeText(this, "좋아요버튼 클릭!", Toast.LENGTH_SHORT).show()
    }
}
