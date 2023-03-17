package com.example.simplecalculator


class Calculator(_expressionString: String) {
    private val expressionString: String

    private var arg1: Double = 0.0
    private var operation: String = ""
    private var arg2: Double = 0.0

    init {
        expressionString = _expressionString
    }

    private fun tryParse():Boolean{

        // define operation as string
        operation = Operators.values().find { op ->
            expressionString.contains(op.sign)
        }?.sign.toString()

        if (operation == "") return false

        val indexOfOperation = expressionString.indexOf(operation)

        return try {
            arg1 = expressionString.substring(0,indexOfOperation).toDouble()
            arg2 = expressionString.substring(indexOfOperation + 1, expressionString.length).toDouble()
            true
        } catch (e:java.lang.Exception){
            false
        }
    }

    fun calculate():String{
        if (tryParse()){
            return when (operation) {
                "-" -> (arg1 - arg2).toString()
                "+" -> (arg1 + arg2).toString()
                "*" -> (arg1 * arg2).toString()
                "/" -> {
                    if (arg2 == 0.0) return ErrorMessage.DIVIDE_BY_ZERO.message
                    else return (arg1 / arg2).toString()
                }
                else -> ErrorMessage.UNEXPECTED.message
            }
        }
        else return ErrorMessage.UNEXPECTED.message
    }

    enum class Operators(val sign: String) {
        MINUS("-"),
        PLUS("+"),
        MULTIPLY("*"),
        DIVISION("/")
    }

    enum class ErrorMessage(val message: String){
        UNEXPECTED("SORRY I CAN'T DO IT YET!"),
        DIVIDE_BY_ZERO("DIVIDE BY ZERO EXCEPTION!")
    }
}

