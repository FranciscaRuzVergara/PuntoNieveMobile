package com.example.proyectonieve.ui



import com.example.proyectonieve.sesion.SessionManager
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ValidarAdminTest : StringSpec({

    beforeTest {
        SessionManager.correoLogeado.value = null
        SessionManager.rolLogeado.value = null
    }

    "Debe retornar true si el correo está en la lista de admins y el rol es Admin" {
        SessionManager.Admins = listOf("fran@nieve.cl", "felipe@nieve.cl")
        SessionManager.correoLogeado.value = "fran@nieve.cl"
        SessionManager.rolLogeado.value = "Admin"

        SessionManager.esAdmin() shouldBe true
    }

    "Debe retornar false si el correo no está en la lista" {
        SessionManager.Admins = listOf("fran@nieve.cl")
        SessionManager.correoLogeado.value = "aaaaa@nieve.cl"
        SessionManager.rolLogeado.value = "Admin"

        SessionManager.esAdmin() shouldBe false
    }

    "Debe retornar false si el correo es null" {
        SessionManager.Admins = listOf("fran@nieve.cl")
        SessionManager.correoLogeado.value = null
        SessionManager.rolLogeado.value = "Admin"

        SessionManager.esAdmin() shouldBe false
    }
})
