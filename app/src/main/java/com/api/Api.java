package com.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.data.Data;
import com.example.data.DataSet;
import com.example.lomaden.R;
import com.example.lomaden.adapter.HomeAdapter;
import com.example.lomaden.adapter.ListAdapter;
import com.example.lomaden.adapter.RecyAdapter;
import com.example.lomaden.adapter.SearchAdapter;
import com.image.download.ImageTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Api {
    private final ProgressDialog progressDialog;
    Context context;
    public Api(Context context){
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Memuat data...");
        progressDialog.setCancelable(true);
    }

    public void open(String data_, String idKosan, ImageView imagesKosan, TextView namaKosan, TextView alamatKosan, TextView descKosan, TextView ratingKosan, TextView priceKosan, TextView fasilitasKosan, RatingBar ratingBar, TextView nomer) {
        progressDialog.show();
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "https://www.chaerul.biz.id/kosan/data.php?data="+data_+"&&id="+idKosan,
                response -> {
                    System.out.println(response);
                    try {
                        JSONArray jsonArray = new JSONObject(response).getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);

                            //key
                            String id = json.getString("id");
                            String title = json.getString("title");
                            String price = json.getString("price");
                            String type = json.getString("type");
                            String address = json.getString("address");
                            String images = json.getString("images");
                            String fasilitas = json.getString("fasilitas");
                            String descriptipn = json.getString("description");
                            int r = json.getInt("rating");
                            String number = json.getString("nomer");
                            float rate = (float)r;

                            new ImageTask(imagesKosan).execute(images);
                            namaKosan.setText(title);
                            priceKosan.setText(price);
                            alamatKosan.setText(address);
                            descKosan.setText(descriptipn);
                            ratingKosan.setText(rate+"");
                            fasilitasKosan.setText(fasilitas);
                            ratingBar.setRating(rate);
                            nomer.setText(number);
                        }

                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                },
                error -> {
                    Toast.makeText(context, "Gagal terhubung", Toast.LENGTH_SHORT).show();
                    System.out.println("error: " + error.getMessage());
                }
        );
        requestQueue.add(stringRequest);
    }

    public void sepeda(ArrayList<DataSet> dataSets, ListAdapter listAdapter) {
        progressDialog.show();
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "https://www.chaerul.biz.id/kosan/sepeda.php",
                response -> {
                    try {
                        JSONArray jsonArray = new JSONObject(response).getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);

                            //key
                            String id = json.getString("id");
                            String title = json.getString("title");
                            String price = json.getString("price");
                            String images = json.getString("images");
                            String address = json.getString("address");
                            String waktu = json.getString("tunjangan");

                            DataSet data = new DataSet(Integer.parseInt(id), title, images, address, price, waktu, "* sisa kendaraan");
                            dataSets.add(data);
                        }

                        progressDialog.dismiss();
                        listAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                },
                error -> {
                    Toast.makeText(context, "Gagal terhubung", Toast.LENGTH_SHORT).show();
                    System.out.println("error: " + error.getMessage());
                }
        );
        requestQueue.add(stringRequest);
    }


    public void motor(ArrayList<DataSet> dataSets, ListAdapter listAdapter) {
        progressDialog.show();
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "https://www.chaerul.biz.id/kosan/motor.php",
                response -> {
                    try {
                        JSONArray jsonArray = new JSONObject(response).getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);

                            //key
                            String id = json.getString("id");
                            String title = json.getString("title");
                            String price = json.getString("price");
                            String images = json.getString("images");
                            String address = json.getString("address");
                            String waktu = json.getString("tunjangan");

                            DataSet data = new DataSet(Integer.parseInt(id), title, images, address, price, waktu, "* sisa kendaraan");
                            dataSets.add(data);
                        }

                        progressDialog.dismiss();
                        listAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                },
                error -> {
                    Toast.makeText(context, "Gagal terhubung", Toast.LENGTH_SHORT).show();
                    System.out.println("error: " + error.getMessage());
                }
        );
        requestQueue.add(stringRequest);
    }

    public void mobil(ArrayList<DataSet> dataSets, ListAdapter listAdapter) {
        progressDialog.show();
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "https://www.chaerul.biz.id/kosan/mobil.php",
                response -> {
                    try {
                        JSONArray jsonArray = new JSONObject(response).getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);

                            //key
                            String id = json.getString("id");
                            String title = json.getString("title");
                            String price = json.getString("price");
                            String images = json.getString("images");
                            String address = json.getString("address");
                            String waktu = json.getString("tunjangan");

                            DataSet data = new DataSet(Integer.parseInt(id), title, images, address, price, waktu, "* sisa kendaraan");
                            dataSets.add(data);
                        }

                        progressDialog.dismiss();
                        listAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                },
                error -> {
                    Toast.makeText(context, "Gagal terhubung", Toast.LENGTH_SHORT).show();
                    System.out.println("error: " + error.getMessage());
                }
        );
        requestQueue.add(stringRequest);
    }
    public void kosan(ArrayList<DataSet> dataSets, ListAdapter listAdapter) {
        progressDialog.show();
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "https://www.chaerul.biz.id/kosan/kos_campur.php",
                response -> {
                    try {
                        JSONArray jsonArray = new JSONObject(response).getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);

                            //key
                            String id = json.getString("id");
                            String title = json.getString("title");
                            String price = json.getString("price");
                            String images = json.getString("images");
                            String address = json.getString("address");
                            String waktu = json.getString("tunjangan");

                            DataSet data = new DataSet(Integer.parseInt(id), title, images, address, price, waktu, "* sisa kamar");
                            dataSets.add(data);
                        }

                        progressDialog.dismiss();
                        listAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                },
                error -> {
                    Toast.makeText(context, "Gagal terhubung", Toast.LENGTH_SHORT).show();
                    System.out.println("error: " + error.getMessage());
                }
        );
        requestQueue.add(stringRequest);
    }
    public void listWithArray(String data_,ArrayList<Data > dataArrayList) {
        progressDialog.show();
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "https://www.chaerul.biz.id/kosan/list.php?data="+data_,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONObject(response).getJSONArray("data_kosan");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);

                            //key
                            String id = json.getString("id");
                            String title = json.getString("title");
                            String price = json.getString("price");
                            String type = json.getString("type");
                            String address = json.getString("address");
                            String images = json.getString("images");
                            String descriptipn = json.getString("description");
                            String rate = json.getString("rating");

                            Data data = new Data();
                            data.setId(id);
                            data.setImages(images);
                            data.setTitle(title);
                            data.setPrice(price);
                            data.setType(type);
                            data.setAddress(address);
                            data.setDescription(descriptipn);
                            data.setData(data_);

                            dataArrayList.add(data);

                        }
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                },
                error -> {
                    Toast.makeText(context, "Gagal terhubung", Toast.LENGTH_SHORT).show();
                    System.out.println("error: " + error.getMessage());
                }
        );
        requestQueue.add(stringRequest);
    }
    public void list_data(String data_, ArrayList<Data> dataArrayList, HomeAdapter homeAdapter) {
        progressDialog.show();
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "https://www.chaerul.biz.id/kosan/list.php?data="+data_,
                response -> {
                    System.out.println(response);
                    System.out.println("");
                    try {
                        JSONArray jsonArray = new JSONObject(response).getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);

                            //key
                            String id = json.getString("id");
                            String title = json.getString("title");
                            String price = json.getString("price");
                            String type = json.getString("type");
                            String address = json.getString("address");
                            String images = json.getString("images");
                            String descriptipn = json.getString("description");
                            String rate = json.getString("rating");
                            String tunjangan = json.getString("tunjangan");
                            String room = json.getString("room");
                            String date = json.getString("tunjangan");

                            int res = 0;
                            String room_ = "";
                            if (data_.equals("kosan")) {
                                res = R.drawable.bed;
                                room_ = room+" kamar";
                            }else{
                                res = R.drawable.accessible;
                                room_ = room+" kursi";
                            }

                            dataArrayList.add(new Data(id, title, price, images, address, type, descriptipn, data_, room_, date, res));

                        }
                        progressDialog.dismiss();
                        homeAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                },
                error -> {
                    Toast.makeText(context, "Gagal terhubung", Toast.LENGTH_SHORT).show();
                    System.out.println("error: " + error.getMessage());
                }
        );
        requestQueue.add(stringRequest);
    }

    public void search(ArrayList<DataSet> dataArrayList, SearchAdapter recyAdapter) {
        progressDialog.show();
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "https://www.chaerul.biz.id/kosan/search.php",
                response -> {
                    try {
                        JSONArray jsonArray = new JSONObject(response).getJSONArray("rekom");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);

                            //key
                            String id = json.getString("id");
                            String title = json.getString("title");
                            String date = json.getString("date");


                            System.out.println("ID: "+id);
                            System.out.println("Title: "+title);

                            dataArrayList.add(new DataSet(Integer.parseInt(id), title, date));

                        }
                        progressDialog.dismiss();
                        recyAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                },
                error -> {
                    Toast.makeText(context, "Gagal terhubung", Toast.LENGTH_SHORT).show();
                    System.out.println("error: " + error.getMessage());
                }
        );
        requestQueue.add(stringRequest);
    }

    public void list_2(String data_, ArrayList<Data> dataArrayList, RecyAdapter recyAdapter) {
        progressDialog.show();
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "https://www.chaerul.biz.id/kosan/list.php?data="+data_,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONObject(response).getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);

                            //key
                            String id = json.getString("id");
                            String title = json.getString("title");
                            String price = json.getString("price");
                            String type = json.getString("type");
                            String address = json.getString("address");
                            String images = json.getString("images");
                            String descriptipn = json.getString("description");
                            String rate = json.getString("rating");
                            String tunjangan = json.getString("tunjangan");
                            int room = json.getInt("room");
                            String date = json.getString("tunjangan");

                            System.out.println("ID: "+id);
                            System.out.println("Title: "+title);
                            System.out.println("Room: "+room);

                            int res = 0;
                            String room_ = "";
                            if (data_.equals("kosan")) {
                                res = R.drawable.bed;
                                room_ = room+" kamar";
                            }else{
                                res = R.drawable.accessible;
                                room_ = room+" kursi";
                            }
                            dataArrayList.add(new Data(id, title, price, images, address, type, descriptipn, data_, room_, date, res));

                        }
                        progressDialog.dismiss();
                        recyAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                },
                error -> {
                    Toast.makeText(context, "Gagal terhubung", Toast.LENGTH_SHORT).show();
                    System.out.println("error: " + error.getMessage());
                }
        );
        requestQueue.add(stringRequest);
    }

    public void open_list(String data_, String people, ArrayList<DataSet> dataSets, ListAdapter adapter){
        progressDialog.show();
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "https://www.chaerul.biz.id/kosan/data.php?data="+data_+"&&people="+people,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONObject(response).getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);

                            //key
                            String id = json.getString("id");
                            String title = json.getString("title");
                            String price = json.getString("price");
                            String type = json.getString("type");
                            String address = json.getString("address");
                            String images = json.getString("images");
                            String descriptipn = json.getString("description");
                            String rate = json.getString("rating");
                            String tunjangan = json.getString("tunjangan");

                            System.out.println("ID: "+id);
                            System.out.println("Title: "+title);

                            dataSets.add(new DataSet(Integer.parseInt(id), title, images, address, price, "", ""));
                        }
                        adapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                },
                error -> {
                    Toast.makeText(context, "Gagal terhubung", Toast.LENGTH_SHORT).show();
                    System.out.println("error: " + error.getMessage());
                    open_list(data_, people, dataSets, adapter);
                }
        );
        requestQueue.add(stringRequest);
    }

}
