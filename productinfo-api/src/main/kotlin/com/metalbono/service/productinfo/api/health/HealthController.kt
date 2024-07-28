package com.metalbono.service.productinfo.api.health

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "HealthController", description = "인스턴스 헬스체크 API")
@RestController
@RequestMapping("/health")
class HealthController {

    @GetMapping
    fun health() = "OK"
}