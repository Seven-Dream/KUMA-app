package com.example.shiota.kotlinandroidsqlite.Model

class NewPlan {
    var id:Int =0
    var title:String?=null
    var timebegin:String?=null
    var timeend:String?=null
    var place:String?=null
    var memo:String?=null

    constructor(){}

    constructor(id:Int, title:String, timebegin:String, timeend:String, place:String, memo:String) {
        this.id=id
        this.title=title
        this.timebegin=timebegin
        this.timeend=timeend
        this.place=place
        this.memo=memo
    }

}