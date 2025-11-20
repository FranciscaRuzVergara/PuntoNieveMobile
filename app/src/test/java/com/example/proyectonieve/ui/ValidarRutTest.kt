package com.example.proyectonieve.ui

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import com.example.proyectonieve.ui.utils.validarRut

class ValidacionRutTest : StringSpec({

    "rut válido con dígito numérico debe retornar true" {
        validarRut("12345678-9") shouldBe true
    }

    "rut válido con dígito K debe retornar true" {
        validarRut("12345678-K") shouldBe true
    }

    "rut inválido con letras en la parte numérica debe retornar false" {
        validarRut("ABC5678-9") shouldBe false
    }

    "rut inválido con longitud distinta de 10 debe retornar false" {
        validarRut("1234567-8") shouldBe false
    }

    "rut inválido sin guion debe retornar false" {
        validarRut("123456789") shouldBe false
    }
})
