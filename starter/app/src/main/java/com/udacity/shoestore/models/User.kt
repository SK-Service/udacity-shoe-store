package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(var userName: String, var password: String) : Parcelable


@Parcelize
data class UserProfile(var userName: String, var password: String) : Parcelable