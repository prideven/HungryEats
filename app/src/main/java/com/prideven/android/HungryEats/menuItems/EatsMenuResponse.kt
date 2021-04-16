package com.prideven.android.hungryeats.menuitems;
data class EatsMenuResponse(
    val address: Address,
    val asap_time: Any,
    val average_rating: Double,
    val business: Business,
    val business_id: Int,
    val composite_score: Int,
    val cover_img_url: String,
    val delivery_fee: Int,
    val delivery_fee_details: DeliveryFeeDetails,
    val delivery_radius: Int,
    val description: String,
    val extra_sos_delivery_fee: Int,
    val fulfills_own_deliveries: Boolean,
    val header_image_url: Any,
    val id: Int,
    val inflation_rate: Int,
    val is_consumer_subscription_eligible: Boolean,
    val is_good_for_group_orders: Boolean,
    val is_newly_added: Boolean,
    val is_only_catering: Boolean,
    val max_composite_score: Int,
    val max_order_size: Any,
    val menus: List<Menu>,
    val merchant_promotions: List<MerchantPromotion>,
    val name: String,
    val number_of_ratings: Int,
    val object_type: String,
    val offers_delivery: Boolean,
    val offers_pickup: Boolean,
    val phone_number: String,
    val price_range: Int,
    val provides_external_courier_tracking: Boolean,
    val service_rate: Int,
    val should_show_store_logo: Boolean,
    val show_store_menu_header_photo: Boolean,
    val show_suggested_items: Boolean,
    val slug: String,
    val special_instructions_max_length: Any,
    val status: String,
    val status_type: String,
    val tags: List<String>,
    val yelp_biz_id: String,
    val yelp_rating: Int,
    val yelp_review_count: Int
)

data class Address(
    val city: String,
    val country: String,
    val id: Int,
    val lat: Double,
    val lng: Double,
    val printable_address: String,
    val shortname: String,
    val state: String,
    val street: String,
    val subpremise: String,
    val zip_code: String
)

data class Business(
    val business_vertical: Any,
    val id: Int,
    val name: String
)

data class DeliveryFeeDetails(
    val discount: Discount,
    val final_fee: FinalFee,
    val original_fee: OriginalFee,
    val surge_fee: SurgeFee
)

data class Menu(
    val id: Int,
    val is_business_enabled: Any,
    val is_catering: Boolean,
    val menu_version: Int,
    val name: String,
    val open_hours: List<List<OpenHour>>,
    val status: String,
    val status_type: String,
    val subtitle: String
)

data class MerchantPromotion(
    val delivery_fee: Int,
    val delivery_fee_monetary_fields: DeliveryFeeMonetaryFields,
    val id: Int,
    val minimum_subtotal: Any,
    val minimum_subtotal_monetary_fields: MinimumSubtotalMonetaryFields,
    val new_store_customers_only: Boolean
)

data class Discount(
    val amount: Amount,
    val description: String,
    val discount_type: String,
    val min_subtotal: MinSubtotal,
    val source_type: String,
    val text: String
)

data class FinalFee(
    val display_string: String,
    val unit_amount: Int
)

data class OriginalFee(
    val display_string: String,
    val unit_amount: Int
)

data class SurgeFee(
    val display_string: String,
    val unit_amount: Int
)

data class Amount(
    val currency: String,
    val display_string: String,
    val unit_amount: Int
)

data class MinSubtotal(
    val currency: String,
    val display_string: String,
    val unit_amount: Int
)

data class OpenHour(
    val hour: Int,
    val minute: Int
)

data class DeliveryFeeMonetaryFields(
    val currency: String,
    val decimal_places: Int,
    val display_string: String,
    val unit_amount: Int
)

data class MinimumSubtotalMonetaryFields(
    val currency: String,
    val decimal_places: Int,
    val display_string: String,
    val unit_amount: Any
)