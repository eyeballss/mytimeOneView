package me.blog.eyeballs.mytimeoneview;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by eye on 16. 10. 24.
 */
public class Data {
    private Info info = new Info();
    private Company company = new Company();
    private MyTime myTime = new MyTime();
    private Google google = new Google();
    private Yelp yelp = new Yelp();

    private ArrayList<Integer> searchResultList = new ArrayList<Integer>();
    private HashMap<Integer, Integer> searchResultMap = new HashMap<Integer, Integer>();

    //control searchResultsList , and searchResultMap
    public void addResultToList(int i){
        searchResultList.add(i);
    }

    public void cleanSearchResultList(){
        searchResultList.clear();
    }

    public Iterator<Integer> getSearchResult(){
        return searchResultList.iterator();
    }


    //getters

    public String getPhoto_url() {
        return info.getPhoto_url();
    }

    public String getName() {
        return info.getName();
    }

    public String getCity() {
        return info.getCity();
    }

    public String getState() {
        return info.getState();
    }

    public String getZip() {
        return info.getZip();
    }

    public String getStreet_address() {
        return info.getStreet_address();
    }

    public String getPhone_number() {
        return info.getPhone_number();
    }

    public String getWebsite() {
        return info.getWebsite();
    }

    public String getBitly_url() {
        return info.getBitly_url();
    }

    public String getId() {
        return info.getId();
    }

    public String getLocation_lat() {
        return info.getLocation_lat();
    }

    public String getLocation_lon() {
        return info.getLocation_lon();
    }

    public Double getMin_price() {
        return info.getMin_price();
    }

    public Double getMax_price() {
        return info.getMax_price();
    }

    public String getDefault_photo_thumb() {
        return info.getDefault_photo_thumb();
    }

    public String getDefault_photo_slate_black() {
        return info.getDefault_photo_slate_black();
    }

    public int getLocations_count() {
        return info.getLocations_count();
    }

    public String getService_name() {
        return info.getService_name();
    }

    public ArrayList<String> getServiceNames() {
        return info.getServiceNames();
    }

    public ArrayList<String> getDuration() {
        return info.getDuration();
    }

    public ArrayList<String> getPhoto_urls() {
        return info.getPhoto_urls();
    }

    public ArrayList<String> getNext_appointment_times() {
        return info.getNext_appointment_times();
    }


    public Double mytime_getRating() {
        return myTime.getRating();
    }

    public int mytime_getReview_count() {
        return myTime.getReview_count();
    }


    public Double yelp_getRating() {
        return yelp.getRating();
    }

    public String yelp_getRating_image_url() {
        return yelp.getRating_image_url();
    }

    public int yelp_getReview_count() {
        return yelp.getReview_count();
    }

    public String yelp_getUrl() {
        return yelp.getUrl();
    }


    public Double google_getRating() {
        return google.getRating();
    }

    public String google_getUrl() {
        return google.getUrl();
    }


    public String getBusiness_diffrences() {
        return company.getBusiness_diffrences();
    }

    public String getLearning_trade() {
        return company.getLearning_trade();
    }

    public String getWhy_love() {
        return company.getWhy_love();
    }

    public String getDescription(){
        return company.getDescription();
    }

    //generate
    static public Data generateData(JSONObject jObject) throws JSONException {
        Data data = new Data();
        if (jObject.has("photo_url"))
            data.info.setPhoto_url(jObject.getString("photo_url"));
        if (jObject.has("name"))
            data.info.setName(jObject.getString("name"));

        //I didn't use 'keys()'
        if (jObject.getJSONObject("modifiers_values").has(" Type of Massage"))
            for (int i = 0; i < jObject.getJSONObject("modifiers_values").getJSONArray(" Type of Massage").length(); i++)
                data.info.addServiceNames(jObject.getJSONObject("modifiers_values").getJSONArray(" Type of Massage").getString(i));

        if (jObject.getJSONObject("modifiers_values").has("Duration"))
            for (int i = 0; i < jObject.getJSONObject("modifiers_values").getJSONArray("Duration").length(); i++)
                data.info.addDuration(jObject.getJSONObject("modifiers_values").getJSONArray("Duration").getString(i));

        if (jObject.has("street_address"))
            data.info.setStreet_address(jObject.getString("street_address"));
        if (jObject.has("city"))
            data.info.setCity(jObject.getString("city"));
        if (jObject.has("state"))
            data.info.setState(jObject.getString("state"));
        if (jObject.has("zip"))
            data.info.setZip(jObject.getString("zip"));

        if (jObject.has("phone_number"))
            data.info.setPhone_number(jObject.getString("phone_number"));
        if (jObject.has("website"))
            data.info.setWebsite(jObject.getString("website"));
        if (jObject.has("bitly_url"))
            data.info.setBitly_url(jObject.getString("bitly_url"));

        if (jObject.has("id"))
            data.info.setId(jObject.getString("id"));
        if (jObject.getJSONObject("location").has("lat"))
            data.info.setLocation_lat(jObject.getJSONObject("location").getString("lat"));
        if (jObject.getJSONObject("location").has("lon"))
            data.info.setLocation_lon(jObject.getJSONObject("location").getString("lon"));

        if (jObject.has("photo_urls"))
            for (int i = 0; i < jObject.getJSONArray("photo_urls").length(); i++)
                data.info.addPhoto_urls(jObject.getJSONArray("photo_urls").getString(i));
        if (jObject.has("min_price"))
            data.info.setMin_price(jObject.getDouble("min_price"));
        if (jObject.has("max_price"))
            data.info.setMax_price(jObject.getDouble("max_price"));

        if (jObject.has("default_photo_thumb"))
            data.info.setDefault_photo_thumb(jObject.getString("default_photo_thumb"));
        if (jObject.has("default_photo_slate_black"))
            data.info.setDefault_photo_slate_black(jObject.getString("default_photo_slate_black"));
        if (jObject.has("service_name"))
            data.info.setService_name(jObject.getString("service_name"));
        if (jObject.has("next_appointment_times"))
            for (int i = 0; i < jObject.getJSONArray("next_appointment_times").length(); i++)
                data.info.addNext_appointment_times(jObject.getJSONArray("next_appointment_times").getString(i));
        if (jObject.has("locations_count"))
            data.info.setLocations_count(jObject.getInt("locations_count"));

        if (jObject.has("mytime_rating"))
            data.myTime.setRating(jObject.getDouble("mytime_rating"));
        if (jObject.has("mytime_review_count"))
            data.myTime.setReview_count(jObject.getInt("mytime_review_count"));

        if (jObject.has("yelp_rating"))
            data.yelp.setRating(jObject.getDouble("yelp_rating"));
        if (jObject.has("yelp_rating_image_url"))
            data.yelp.setRating_image_url(jObject.getString("yelp_rating_image_url"));
        if (jObject.has("yelp_review_count"))
            data.yelp.setReview_count(jObject.getInt("yelp_review_count"));
        if (jObject.has("yelp_url"))
            data.yelp.setUrl(jObject.getString("yelp_url"));

        if (jObject.has("google_rating"))
            data.google.setRating(jObject.getDouble("google_rating"));
        if (jObject.has("google_url"))
            data.google.setUrl(jObject.getString("google_url"));

        if (jObject.getJSONObject("company").has("business_diffrences"))
            data.company.setBusiness_diffrences(jObject.getJSONObject("company").getString("business_diffrences"));
        if (jObject.getJSONObject("company").has("learning_trade"))
            data.company.setLearning_trade(jObject.getJSONObject("company").getString("learning_trade"));
        if (jObject.getJSONObject("company").has("why_love"))
            data.company.setWhy_love(jObject.getJSONObject("company").getString("why_love"));
        if (jObject.getJSONObject("company").has("name"))
            data.company.setName(jObject.getJSONObject("company").getString("name"));
        if (jObject.getJSONObject("company").has("description"))
            data.company.setDescription(jObject.getJSONObject("company").getString("description"));

        if (jObject.getJSONObject("company").has("street_address"))
            data.company.setStreet_address(jObject.getJSONObject("company").getJSONObject("locations").getString("street_address"));
        if (jObject.getJSONObject("company").has("street_address_2"))
            data.company.setStreet_address_2(jObject.getJSONObject("company").getJSONObject("locations").getString("street_address_2"));
        if (jObject.getJSONObject("company").has("city"))
            data.company.setCity(jObject.getJSONObject("company").getJSONObject("locations").getString("city"));
        if (jObject.getJSONObject("company").has("state"))
            data.company.setState(jObject.getJSONObject("company").getJSONObject("locations").getString("state"));
        if (jObject.getJSONObject("company").has("CA"))
            data.company.setCA(jObject.getJSONObject("company").getJSONObject("locations").getString("CA"));
        if (jObject.getJSONObject("company").has("zip_code"))
            data.company.setZip_code(jObject.getJSONObject("company").getJSONObject("locations").getString("zip_code"));
        if (jObject.getJSONObject("company").has("phone_number"))
            data.company.setPhone_number(jObject.getJSONObject("company").getJSONObject("locations").getString("phone_number"));
        if (jObject.getJSONObject("company").has("mobile_number"))
            data.company.setMobile_number(jObject.getJSONObject("company").getJSONObject("locations").getString("mobile_number"));
        if (jObject.getJSONObject("company").has("lat"))
            data.company.setLocation_lat(jObject.getJSONObject("company").getJSONObject("locations").getString("lat"));
        if (jObject.getJSONObject("company").has("lng"))
            data.company.setLocation_lng(jObject.getJSONObject("company").getJSONObject("locations").getString("lng"));


        Log.d("Data.generateData()", "done");
        return data;

    }
}

class Info {

    private String photo_url;
    private String name;
    private ArrayList<String> serviceNames = new ArrayList<String>();
    private ArrayList<String> duration = new ArrayList<String>();

    private String street_address;
    private String city;
    private String state;
    private String zip;

    private String phone_number;
    private String website;
    private String bitly_url;

    private String id; //primary key
    private String location_lat;
    private String location_lon;

    private ArrayList<String> photo_urls = new ArrayList<String>();
    private Double min_price;
    private Double max_price;

    private String default_photo_thumb;
    private String default_photo_slate_black;
    private String service_name;
    private int locations_count;
    private ArrayList<String> next_appointment_times = new ArrayList<String>();

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBitly_url() {
        return bitly_url;
    }

    public void setBitly_url(String bitly_url) {
        this.bitly_url = bitly_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation_lat() {
        return location_lat;
    }

    public void setLocation_lat(String location_lat) {
        this.location_lat = location_lat;
    }

    public String getLocation_lon() {
        return location_lon;
    }

    public void setLocation_lon(String location_lon) {
        this.location_lon = location_lon;
    }

    public Double getMin_price() {
        return min_price;
    }

    public void setMin_price(Double min_price) {
        this.min_price = min_price;
    }

    public Double getMax_price() {
        return max_price;
    }

    public void setMax_price(Double max_price) {
        this.max_price = max_price;
    }

    public String getDefault_photo_thumb() {
        return default_photo_thumb;
    }

    public void setDefault_photo_thumb(String default_photo_thumb) {
        this.default_photo_thumb = default_photo_thumb;
    }

    public String getDefault_photo_slate_black() {
        return default_photo_slate_black;
    }

    public void setDefault_photo_slate_black(String default_photo_slate_black) {
        this.default_photo_slate_black = default_photo_slate_black;
    }

    public int getLocations_count() {
        return locations_count;
    }

    public void setLocations_count(int locations_count) {
        this.locations_count = locations_count;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }


    public ArrayList<String> getServiceNames() {
        return serviceNames;
    }

    public ArrayList<String> getDuration() {
        return duration;
    }

    public ArrayList<String> getPhoto_urls() {
        return photo_urls;
    }

    public ArrayList<String> getNext_appointment_times() {
        return next_appointment_times;
    }

    public void addServiceNames(String str) {
        this.serviceNames.add(str);
    }

    public void addDuration(String str) {
        this.duration.add(str);
    }

    public void addPhoto_urls(String str) {
        this.photo_urls.add(str);
    }

    public void addNext_appointment_times(String str) {
        this.next_appointment_times.add(str);
    }
}

class MyTime {

    private Double rating;
    private int review_count=-1;

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }
}

class Yelp {

    private Double rating;
    private String rating_image_url;
    private int review_count=-1;
    private String url;

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getRating_image_url() {
        return rating_image_url;
    }

    public void setRating_image_url(String rating_image_url) {
        this.rating_image_url = rating_image_url;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

class Google {

    private Double rating;
    private String url;

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

class Company {
    private String business_diffrences;
    private String learning_trade;
    private String why_love;
    private String name;
    private String description;
    private String street_address;
    private String street_address_2;
    private String city;
    private String state;
    private String CA;
    private String zip_code;
    private String phone_number;
    private String mobile_number;
    private String location_lat;
    private String location_lng;

    public String getBusiness_diffrences() {
        return business_diffrences;
    }

    public void setBusiness_diffrences(String business_diffrences) {
        this.business_diffrences = business_diffrences;
    }

    public String getLearning_trade() {
        return learning_trade;
    }

    public void setLearning_trade(String learning_trade) {
        this.learning_trade = learning_trade;
    }

    public String getWhy_love() {
        return why_love;
    }

    public void setWhy_love(String why_love) {
        this.why_love = why_love;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getStreet_address_2() {
        return street_address_2;
    }

    public void setStreet_address_2(String street_address_2) {
        this.street_address_2 = street_address_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCA() {
        return CA;
    }

    public void setCA(String CA) {
        this.CA = CA;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getLocation_lat() {
        return location_lat;
    }

    public void setLocation_lat(String location_lat) {
        this.location_lat = location_lat;
    }

    public String getLocation_lng() {
        return location_lng;
    }

    public void setLocation_lng(String location_lng) {
        this.location_lng = location_lng;
    }
}