package com.example.a541j.Models;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Chat {
   public Message message;
  public   ArrayList<Room> room;

    public Chat(Message message) {
        this.message = message;
    }

    public Chat(ArrayList<Room> room) {
        this.room = room;
    }
}
