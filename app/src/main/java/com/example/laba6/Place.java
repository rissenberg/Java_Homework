package com.example.laba6;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.yandex.mapkit.geometry.Point;

import java.io.Serializable;
import java.util.ArrayList;

public class Place implements Parcelable {
    private Point point;
    private String info;
    private String name;

    protected Place(Parcel in) {
        info = in.readString();
        name = in.readString();
        point = new Point(in.readDouble(), in.readDouble());
    }
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Place() {
    }

    public Place(Point point, String info, String name) {
        this.point = point;
        this.info = info;
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(info);
        dest.writeString(name);
        dest.writeDouble(point.getLatitude());
        dest.writeDouble(point.getLongitude());
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public static ArrayList<Place> getPlaces() {
        ArrayList<Place> places = new ArrayList<Place>();
        Place place1 = new Place(new Point(55.760221, 37.618561),"Большой театр - один из самых известных театров оперы и балета. Находится в Москве на Театральной площади. Он был построен по проекту архитектора Христиана Розберга в 1776-1789 г. и назывался тогда Большим Петровским театром. В 1805 г. здание театра сгорело, после чего К. Росси построил новое деревянное здание на Арбатской площади. Во время пожара 1812 г. оно также сгорело. В 1825 г. открылось новое здание театра, построенное по проекту О. Бове.","Большой театр");
        Place place2 = new Place(new Point( 55.752652,37.623086),"Собор Василия Блаженного – православный храм в центре Москвы, полное название которого звучит как Собор Покрова Пресвятой Богородицы, что на Рву. Памятник русской архитектуры XVI в. находится на Красной площади и включен в списки Всемирного наследия ЮНЕСКО. Собор представляет собой комплекс из одиннадцати церквей на едином подклете, среди которых центральная освящена в честь Покрова Богородицы.","Храм Василия Блаженного");
        Place place3 = new Place(new Point(55.826398, 37.637875),"Выставка достижений народного хозяйства — крупнейший экспозиционный, музейный и рекреационный комплекс в мире. С момента открытия 1 августа 1939 года название менялось несколько раз — ВСХВ, ВДНХ СССР, ВВЦ. Сегодня ВДНХ — уникальное пространство музейно-выставочных проектов, международных деловых выставок и конгрессов, фестивалей и праздников.","ВДНХ");
        Place place4 = new Place(new Point(55.741556, 37.620028),"Государственная Третьяковская галерея — московский художественный музей, основанный в 1856 году купцом Павлом Третьяковым. В 1867-м галерея была открыта для посещения, а в 1892 году передана в собственность Москве. На момент передачи коллекция музея насчитывала 1276 картин, 471 рисунок, десять скульптур русских художников, а также 84 картины иностранных мастеров.","Третьяковская галерея");
        Place place5 = new Place(new Point(55.744561, 37.605463),"Храм Христа Спасителя, или Храм Рождества Христова, — кафедральный собор Русской православной церкви, расположенный в центральной части Москвы на улице Волхонке. Существующее сооружение, построенное в 1990-х годах, является воссозданием одноимённого храма, разрушенного в 1931 году. Самый большой православный храм России.","Храм Христа Спасителя");
        Place place6 = new Place(new Point(55.765990, 37.684560)," МГТУ — российский национальный исследовательский университет, научный центр, особо ценный объект культурного наследия народов России","МГТУ им. Н.Э.Баумана");

        places.add(place1); places.add(place2); places.add(place3); places.add(place4); places.add(place5); places.add(place6);
        return places;
    }
}
