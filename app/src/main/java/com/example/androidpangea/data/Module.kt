//package com.example.androidpangea.data
//
//import androidx.room.Room
//import retrofit2.Retrofit
//
//val deepLinkModule = module {
//    factory<DeepLinkFacade> { DeepLinkFacadeImpl() }
//}
//
//val databaseModule = module {
//    single {
//        Room.databaseBuilder(
//            androidApplication(),
//            CountryDatabase::class.java,
//            DATABASE_NAME
//        ).build()
//    }
//    factory { get<CountryDatabase>().countryCacheDao }
//}
//
//val repositoryModule = module {
//    single<CountryRepository> { CountryRepositoryImpl(get(), get(), get(), get(), get()) }
//    factory<EmailAuthRepository> { EmailAuthRepositoryImpl(get(), get()) }
//    factory<FacebookAuthRepository> { FacebookAuthRepositoryImpl(get(), get()) }
//    factory<GoogleSignInRepository> {
//        GoogleSignInRepositoryImpl(
//            get(),
//            get(),
//            get(),
//            get(named(Constants.SIGN_IN_REQUEST)),
//            get(named(Constants.SIGN_UP_REQUEST))
//        )
//    }
//    factory<TwitterAuthRepository> { TwitterAuthRepositoryImpl(get(), get()) }
//    factory<ProfileRepository> { ProfileRepositoryImpl(get(), get(), get()) }
//}
//
//val viewModelModules = module {
//    viewModel { CountryViewModel(get()) }
//    viewModel { SignInViewModel(get(), get(), get(), get(), get()) }
//    viewModel { SignUpViewModel(get()) }
//    viewModel { AuthViewModel(get()) }
//    viewModel { ProfileViewModel(get()) }
//    viewModel { ForgotPasswordViewModel(get(), get()) }
//    viewModel { DeepLinkViewModel(get()) }
//    viewModel { TabViewModel(get()) }
//}
//
//val retrofitModules = module {
//    single<CountryInfoApi> {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL_COUNTRIES)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//            .create()
//    }
//    single<CountryHistoryApi> {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL_HISTORIES)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//            .create()
//    }
//}
