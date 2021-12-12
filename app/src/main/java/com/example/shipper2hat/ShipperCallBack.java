package com.example.shipper2hat;

import java.util.ArrayList;

public interface ShipperCallBack {
    void onSuccess(ArrayList<Shipper> lists);
    void onError(String message);
}
