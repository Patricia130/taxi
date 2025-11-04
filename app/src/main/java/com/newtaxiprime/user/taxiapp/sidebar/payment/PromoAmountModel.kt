package com.newtaxiprime.user.taxiapp.sidebar.payment

/**
 * @author Seen Technologies
 * 
 * @package com.newtaxiprime.user
 * @subpackage Side_Bar.payment
 * @category Model
 */


class PromoAmountModel {

    var promoId: String=""
    var promoCode: String=""
    /**
     * Getter and setter for promo code details method
     */

    var promoAmount: String=""
    var promoExp: String=""


    constructor() {

    }

    constructor(
            promo_amount: String,
            promo_id: String,
            promo_code: String,
            promo_exp: String) {

        this.promoAmount = promo_amount
        this.promoId = promo_id
        this.promoCode = promo_code
        this.promoExp = promo_exp

    }
}
