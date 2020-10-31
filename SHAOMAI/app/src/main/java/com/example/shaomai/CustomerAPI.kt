
import com.example.shaomai.Customer
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface CustomerAPI {


    @POST("register")
    @FormUrlEncoded
    fun signup(
        @Field("customer_name") customer_name: String,
        @Field("customer_tel") customer_tel: Int,
        @Field("customer_mail") customer_mail: String,
        @Field("customer_pass1") customer_pass1: String,
        @Field("customer_pass2") customer_pass2: String,
        ): Call<Customer>


    @POST("login")
    @FormUrlEncoded
    fun logincus(
        @Field("customer_mail") customer_mail: String,
        @Field("customer_pass1") customer_pass1: String
    ): Call<Customer>





}