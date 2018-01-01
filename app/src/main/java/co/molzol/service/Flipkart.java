package co.molzol.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import co.molzol.model.flipkart.Deal;
import co.molzol.model.flipkart.DealsOfTheDay;
import co.molzol.model.flipkart.ProductInfoList;
import co.molzol.util.Constants;

/**
 * Created by hp on 14-02-2016.
 */
public class Flipkart {

    private static RestTemplate restTemplate;
    private static HttpHeaders requestHeaders;
    private static HttpEntity requestEntity;

    static{
        restTemplate = new RestTemplate();
        requestHeaders = new HttpHeaders();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        requestHeaders.set("Fk-Affiliate-Id", "careeramr");
        requestHeaders.set("Fk-Affiliate-Token","9fb75352eb43425597731e5fa3064462");
        requestEntity = new HttpEntity(requestHeaders);
    }

    public static DealsOfTheDay fetchDeals(){
        try {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Constants.FETCH_DEALS);

        ResponseEntity<DealsOfTheDay> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, requestEntity, DealsOfTheDay.class);

        return response.getBody();
        } catch (Exception e) {
            DealsOfTheDay dod = new DealsOfTheDay();
            List<Deal> dealList = new ArrayList<Deal>();
            Deal d = new Deal();
            dealList.add(d);
            dealList.add(d);
            dealList.add(d);
            dealList.add(d);
            dealList.add(d);
            dealList.add(d);
            dealList.add(d);
            dealList.add(d);
            dealList.add(d);
            dealList.add(d);
            dod.setDotdList(dealList);

            return dod;
        }
    }

    public static ProductInfoList searchProducts(String query, int count){

        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Constants.SEARCH_PRODUCT)
                    .queryParam("query", query)
                    .queryParam("resultCount", count);

            ResponseEntity<ProductInfoList> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, requestEntity, ProductInfoList.class);
            return response.getBody();
        } catch (Exception e) {

            return null;
        }


    }

}