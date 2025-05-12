package com.aab.dev_connect.contoller;

import com.aab.dev_connect.dto.DashboardDataResponse;
import com.aab.dev_connect.dto.ProjectResponseDataType;
import com.aab.dev_connect.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private DashboardService dashboardService;
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    /**
     * This method is used to get the dashboard data for a user.
     *
     * @param userName the username of the user
     * @return the dashboard data for the user
     */
    @Operation(
            summary = "Dashboard Page",
            description = "Returns user dashboard data"
    )
    @GetMapping("/{userName}")
    public ResponseEntity<DashboardDataResponse> getUserDashboardData(@PathVariable String userName) {

        DashboardDataResponse dashboardDataResponse =   this.dashboardService.getDashboardData(userName);
        return ResponseEntity.ok(dashboardDataResponse);
    }

}
