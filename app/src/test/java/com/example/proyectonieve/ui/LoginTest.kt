package com.example.proyectonieve.ui
import com.example.proyectonieve.sesion.SessionManager
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginTest {
    @BeforeEach
    fun limpiar() {
        SessionManager.rolLogeado.value = null
        SessionManager.correoLogeado.value = null
    }

    @Test
    fun loginAsincronico() = runTest {
        SessionManager.rolLogeado.value = null
        val emailPrueba = "fran@nieve.cl"
        val rolPrueba = "Admin"

        launch {
            SessionManager.iniciarSesionAsync(emailPrueba, rolPrueba)
        }

        SessionManager.rolLogeado.value shouldBe null

        advanceTimeBy(1000)
        SessionManager.rolLogeado.value shouldBe null

        advanceTimeBy(600)

        SessionManager.rolLogeado.value shouldBe "Admin"
        SessionManager.esAdmin() shouldBe true
        SessionManager.correoLogeado.value shouldBe "fran@nieve.cl"
    }
}