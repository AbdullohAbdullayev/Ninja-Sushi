package uz.sushi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.sushi.entity.User;
import uz.sushi.entity.enums.ProductType;
import uz.sushi.oap.CurrentUser;
import uz.sushi.payload.ApiResult;
import uz.sushi.payload.ProductCollectionDTO;
import uz.sushi.payload.add.AddProduct;
import uz.sushi.payload.dto.ProductDTO;
import uz.sushi.service.ProductService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = ProductController.PRODUCT_CONTROLLER_BASE_PATH)
@RequiredArgsConstructor
public class ProductController {
    public static final String PRODUCT_CONTROLLER_BASE_PATH = "/api/product";
    public static final String TOP_POSITIONS_SUSHI = "/top-positions-products";
    public static final String ADD = "/";
    public static final String EDIT = "/{id}";
    public static final String ALL = "/{type}";
    private final ProductService productService;

    @PostMapping(value = TOP_POSITIONS_SUSHI)
    ApiResult<ProductCollectionDTO> topProducts(@CurrentUser User user) {
        return productService.topProducts(user);
    }


    @PostMapping(ADD)
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    ApiResult<ProductDTO> add(@RequestBody AddProduct addProduct) {
        return productService.addProduct(addProduct);
    }

    @PutMapping(EDIT)
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    ApiResult<ProductDTO> edit(@PathVariable Integer id, @RequestBody AddProduct addProduct) {
        return productService.editProduct(id, addProduct);
    }

    @GetMapping(ALL)
    ApiResult<List<ProductDTO>> getAll(@PathVariable String type) {
        return ApiResult.successResponse(productService.productList(ProductType.valueOf(type.toUpperCase()), 0, 500, Set.of()));
    }


}
