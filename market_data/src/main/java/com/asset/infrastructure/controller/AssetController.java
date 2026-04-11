package com.asset.infrastructure.controller;

import com.asset.application.dto.response.AssetResponse;
import com.asset.application.service.AssetService;
import com.finance_common.response.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assets")
@AllArgsConstructor
public class AssetController {
    private final AssetService assetService;

    @GetMapping
    public ApiResponse<List<AssetResponse>> getAll()
    {
        return ApiResponse.ok(assetService.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<AssetResponse> getById(@PathVariable UUID id)
    {
        return ApiResponse.ok(assetService.getById(id));
    }

    // GET /api/assets/search?keyword=VIC&page=0&size=20
    // @RequestParam: lấy query parameter từ URL
    @GetMapping("/search")
    public ApiResponse<List<AssetResponse>> search(@RequestParam String keyword)
    {
        return ApiResponse.ok(assetService.search(keyword));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deactive(@PathVariable UUID id)
    {
        return ApiResponse.ok(assetService.deactiveAsset(id));
    }
}
