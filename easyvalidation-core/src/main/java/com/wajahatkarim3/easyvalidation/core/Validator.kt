package com.wajahatkarim3.easyvalidation.core

import com.wajahatkarim3.easyvalidation.core.rules.*
import java.math.BigDecimal
import java.math.BigInteger

/**
 * The core Validator builder class for validation operations and checks!
 *
 * This class allows developers to process single or multiple validation checks on input views.
 *
 * @author Wajahat Karim
 * @date 08/05/18
 */
class Validator(val text: String)
{
    /*
     * Boolean to determine whether all the validations have passed successfully!
     * If any validation check is failed, then the value to
     * false and result is returned to developer
     */
    private var isValid = true

    /*
     The error message to be sent in the error callback
     */
    private var errorMessage: String = ""

    /*
     * In case of validation error or failure, this callback is invoked
     */
    var errorCallback: ((message: String) -> Unit)? = null

    /*
     * In case of validation success, this callback is invoked
     */
    var successCallback: (() -> Unit)? = null

    /*
     * The rules list to check for validation
     */
    var rulesList = ArrayList<BaseRule>()

    /*
     * Performs the validation check and returns true or false.
     * Also invokes success and error callbacks if non null.
     */
    fun check() : Boolean
    {
        for (rule in rulesList)
        {
            if (!rule.validate(text))
            {
                setError(rule.getErrorMessage())
                break
            }
        }

        // Invoking callbacks
        if (isValid)
            successCallback?.invoke()
        else
            errorCallback?.invoke(errorMessage)

        return isValid
    }

    fun setError(message: String)
    {
        isValid = false
        errorMessage = message
    }

    fun addRule(rule: BaseRule) : Validator
    {
        rulesList.add(rule)
        return this
    }

    fun addErrorCallback(callback: (message: String) -> Unit) : Validator
    {
        errorCallback = callback
        return this
    }

    fun addSuccessCallback(callback: () -> Unit) : Validator
    {
        successCallback = callback
        return this
    }

    // Rules
    fun nonEmpty() : Validator
    {
        addRule(NonEmptyRule())
        return this
    }

    fun minLength(length: Int) : Validator
    {
        addRule(MinLengthRule(length))
        return this
    }

    fun maxLength(length: Int) : Validator
    {
        addRule(MaxLengthRule(length))
        return this
    }

    fun validEmail() : Validator
    {
        addRule(EmailRule())
        return this
    }

    fun validNumber() : Validator
    {
        addRule(ValidNumberRule())
        return this
    }

    fun greaterThan(number: Number) : Validator
    {
        addRule(GreaterThanRule(number))
        return this
    }

    fun greaterThanOrEqual(number: Number) : Validator
    {
        addRule(GreaterThanOrEqualRule(number))
        return this
    }

    fun lessThan(number: Number) : Validator
    {
        addRule(LessThanRule(number))
        return this
    }

    fun lessThanOrEqual(number: Number) : Validator
    {
        addRule(LessThanOrEqualRule(number))
        return this
    }

    fun numberEqualTo(number: Number) : Validator
    {
        addRule(NumberEqualToRule(number))
        return this
    }

    fun allLowerCase() : Validator
    {
        addRule(AllLowerCaseRule())
        return this
    }

    fun allUpperCase() : Validator
    {
        addRule(AllUpercCaseRule())
        return this
    }

    fun atleastOneUpperCase() : Validator
    {
        addRule(AtLeastOneUpercCaseRule())
        return this
    }

    fun atleastOneLowerCase() : Validator
    {
        addRule(AtLeastOneLowerCaseRule())
        return this
    }

    fun atleastOneNumber() : Validator
    {
        addRule(AtLeastOneNumberCaseRule())
        return this
    }

    fun noNumbers() : Validator
    {
        addRule(NoNumbersRule())
        return this
    }

    fun onlyNumbers() : Validator
    {
        addRule(OnlyNumbersRule())
        return this
    }

    fun startWithNumber() : Validator
    {
        addRule(StartsWithNumberRule())
        return this
    }

    fun startWithNonNumber() : Validator
    {
        addRule(StartsWithNoNumberRule())
        return this
    }

    fun noSpecialCharacters() : Validator
    {
        addRule(NoSpecialCharacterRule())
        return this
    }

    fun atleastOneSpecialCharacters() : Validator
    {
        addRule(AtleastOneSpecialCharacterRule())
        return this
    }

    fun textEqualTo(target: String) : Validator
    {
        addRule(TextEqualToRule(target))
        return this
    }

    fun textNotEqualTo(target: String) : Validator
    {
        addRule(TextNotEqualToRule(target))
        return this
    }

    fun startsWith(target: String) : Validator
    {
        addRule(StartsWithRule(target))
        return this
    }

    fun endsWith(target: String) : Validator
    {
        addRule(EndsWithRule(target))
        return this
    }

    fun contains(target: String) : Validator
    {
        addRule(ContainsRule(target))
        return this
    }

    fun notContains(target: String) : Validator
    {
        addRule(NotContainsRule(target))
        return this
    }

    fun creditCardNumber() : Validator
    {
        addRule(MinLengthRule(16))
        addRule(MaxLengthRule(16))
        addRule(CreditCardRule())
        return this
    }

    fun creditCardNumberWithSpaces() : Validator
    {
        addRule(MinLengthRule(19))
        addRule(MaxLengthRule(19))
        addRule(CreditCardWithSpacesRule())
        return this
    }

    fun creditCardNumberWithDashes() : Validator
    {
        addRule(MinLengthRule(19))
        addRule(MaxLengthRule(19))
        addRule(CreditCardWithDashesRule())
        return this
    }

    fun validUrl() : Validator
    {
        addRule(ValidUrlRule())
        return this
    }

    fun regex(pattern: String) : Validator
    {
        addRule(RegexRule(pattern))
        return this
    }
}