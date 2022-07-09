package com.example.my_dictionary;

import com.example.my_dictionary.Models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse,String message);
    void onError(String message);
}
