import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.*;

import api.CustomerInfoController;
import dao.LocalCustomerInfoDataAccessService;
import service.CustomerInfoService;

class Backend{
    static int PORT = 8080;
    public static void main(String[] args) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", PORT), 0);

        // initilize apis

        CustomerInfoService    customerInfoService = new CustomerInfoService(new LocalCustomerInfoDataAccessService());
        CustomerInfoController customerInfoController = new CustomerInfoController(customerInfoService);

        server.start();
        System.out.printf("Server started on port %d...\n", PORT);
    }
}