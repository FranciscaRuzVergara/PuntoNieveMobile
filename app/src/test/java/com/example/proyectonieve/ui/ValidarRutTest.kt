package com.example.proyectonieve.ui

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import com.example.proyectonieve.ui.utils.validarRut

class ValidacionRutTest : StringSpec({

    "rut válido con dígito numérico " {
        validarRut("12345678-9") shouldBe true
    }

    "rut válido con dígito K " {
        validarRut("12345678-K") shouldBe true
    }

    "rut inválido con letras " {
        validarRut("ABC5678-9") shouldBe false
    }

    "rut inválido con longitud distinta " {
        validarRut("1234567-8") shouldBe false
    }

    "rut inválido sin guion " {
        validarRut("123456789") shouldBe false
    }
})
