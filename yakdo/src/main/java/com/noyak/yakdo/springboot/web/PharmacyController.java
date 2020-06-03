package com.noyak.yakdo.springboot.web;

import com.noyak.yakdo.springboot.service.PharmacyService;
import com.noyak.yakdo.springboot.web.dto.pharmacy.PharmacyResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class PharmacyController {

    private final PharmacyService pService;

    @GetMapping("/pharmacy/{p_id}")
    public ResponseEntity<Map<String, Object>> findPharmacyById(@PathVariable int p_id) {
        log.trace("findPharmacyById: {}", p_id);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            PharmacyResponseDto pharmacy = pService.findById(p_id);

            resultMap.put("status", true);
            resultMap.put("info", pharmacy);
            status = HttpStatus.ACCEPTED;
        } catch (RuntimeException e) {
            log.error("약국정보 조회 실패", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/pharmacy/list/p_x/{p_x}/p_y/{p_y}")
    public List<PharmacyResponseDto> findByPosition(@PathVariable double p_x, @PathVariable double p_y) {
        return pService.findByPosition(p_x, p_y);
    }

    @GetMapping("/pharmacy/list/address/{address}/p_x/{p_x}/p_y/{p_y}")
    public List<PharmacyResponseDto> findWithAddress(@PathVariable String address, @PathVariable double p_x, @PathVariable double p_y) {
        return pService.findWithAddress(address, p_x, p_y);
    }
}

