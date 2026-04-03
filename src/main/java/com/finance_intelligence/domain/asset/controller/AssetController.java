package com.finance_intelligence.domain.asset.controller;

import com.finance_intelligence.common.response.ApiResponse;
import com.finance_intelligence.domain.asset.dto.response.AssetResponse;
import com.finance_intelligence.domain.asset.service.AssetService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assets")
@AllArgsConstructor
public class AssetController {
    private final AssetService assetService;

    @GetMapping("/{id}")
    public ApiResponse<AssetResponse> getById(@PathVariable UUID id)
    {
        return ApiResponse.<AssetResponse>builder()
                .message("Get asset successfully")
                .data(assetService.getById(id))
                .build();
    }

    // GET /api/assets/search?keyword=VIC&page=0&size=20
    // @RequestParam: lấy query parameter từ URL
    @GetMapping("/search")
    public ApiResponse<List<AssetResponse>> search(@RequestParam String keyword)
    {
        return ApiResponse.<List<AssetResponse>>builder()
                .data(assetService.search(keyword))
                .message("Get asset by search (keyword)")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deactive(UUID id)
    {
        return ApiResponse.<String>builder()
                .message(assetService.deactiveAsset(id))
                .build();
    }


}
