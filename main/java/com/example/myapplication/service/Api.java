package com.example.myapplication.service;

import com.example.myapplication.PostModel.Post;
import com.example.myapplication.UserModel.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("users")
    Call<List<User>> getUser();

    @GET("posts")
    Call<List<Post>> getPost();

}
