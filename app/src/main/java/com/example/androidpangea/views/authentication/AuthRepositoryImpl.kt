//package com.example.androidpangea.views.authentication
//
//import com.firebase.ui.auth.data.model.Resource
//import com.google.firebase.auth.AuthResult
//import com.google.firebase.auth.FirebaseAuth
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.internal.NopCollector.emit
//import kotlinx.coroutines.tasks.await
//import javax.inject.Inject
//
//
//class AuthRepositoryImpl @Inject constructor(
//    private val firebaseAuth: FirebaseAuth
//) : AuthRepository {
//    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
//        return flow {
//            emit(value = Resource.Loading())
//            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
//            emit(value = Resource.Success(data = result))
//        }.catch {
//            emit(value = Resource.Error(it.message.toString()))
//        }
//    }
//
//    override fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> {
//        return flow {
//            emit(value = Resource.Loading())
//            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
//            emit(value = Resource.Success(data = result))
//        }.catch {
//            emit(value = Resource.Error(it.message.toString()))
//        }
//    }
//}
