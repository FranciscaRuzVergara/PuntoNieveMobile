package com.example.proyectonieve.ui

import com.example.proyectonieve.sesion.SessionManager
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkObject

class ValidarAdminTest : StringSpec({

    beforeTest {
        mockkObject(SessionManager)
    }


    "El correo no está en la lista" {
        every { SessionManager.Admins } returns listOf("fran@nieve.cl")
        every { SessionManager.correoLogeado.value } returns "aaaaa@nieve.cl"
        every { SessionManager.rolLogeado.value } returns "Admin"

        SessionManager.esAdmin() shouldBe false
    }

    "El correo es null" {
        every { SessionManager.Admins } returns listOf("fran@nieve.cl")
        every { SessionManager.correoLogeado.value } returns null
        every { SessionManager.rolLogeado.value } returns "Admin"

        SessionManager.esAdmin() shouldBe false
    }
})
