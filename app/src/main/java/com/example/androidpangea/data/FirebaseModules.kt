//package com.example.androidpangea.data
//
//import com.google.android.gms.auth.api.identity.BeginSignInRequest
//import com.google.android.gms.auth.api.identity.Identity
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.storage.FirebaseStorage
//
//val firebaseModules = module {
//    single { FirebaseAuth.getInstance() }
//    single { FirebaseFirestore.getInstance() }
//    single { Identity.getSignInClient(androidApplication()) }
//    single { FirebaseStorage.getInstance() }
//
//    single(named(Constants.SIGN_IN_REQUEST)) {
//        BeginSignInRequest.builder()
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    .setServerClientId(BuildConfig.GOOGLE_WEB_CLIENT_ID)
//                    .setFilterByAuthorizedAccounts(true)
//                    .build()
//            ).setAutoSelectEnabled(true)
//            .build()
//    }
//
//    single(named(Constants.SIGN_UP_REQUEST)) {
//        BeginSignInRequest.builder()
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    .setServerClientId(BuildConfig.GOOGLE_WEB_CLIENT_ID)
//                    .setFilterByAuthorizedAccounts(false)
//                    .build()
//            )
//            .build()
//    }
//
//    single {
//        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(BuildConfig.GOOGLE_WEB_CLIENT_ID)
//            .requestEmail()
//            .build()
//    }
//
//    single { GoogleSignIn.getClient(androidApplication(), get()) }
//}
