package com.iso.client.configuration;

import com.iso.client.utility.codec.SmartVistaDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import org.jpos.iso.ISOException;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

//created By Dwi S - Agustus 2020

@Configuration
public class PackagerConfig {

    private GenericPackager packagerNetworkManagement;
    private GenericPackager packagerFinancial;

    /**
     * Baca file NetworkManagement.xml untuk keperluan JPOS dalam membentuk ISO8583
     * Message untuk network management (signon, echo, signoff)
     *
     * @return
     * @throws ISOException
     * @throws IOException
     */
    public GenericPackager getPackagerNetworkManagement() throws ISOException, IOException {
        if (packagerNetworkManagement == null) {
            Resource resource = new ClassPathResource("NetworkManagement.xml");
            packagerNetworkManagement = new GenericPackager();
            packagerNetworkManagement.readFile(resource.getInputStream());
        }
        return packagerNetworkManagement;
    }

    /**
     * Baca file FinancialTransaction.xml untuk keperluan JPOS dalam membentuk ISO8583
     * Message untuk financial : inquiry dan payment
     *
     * @return
     * @throws ISOException
     * @throws IOException
     */
    public GenericPackager getPackagerFinancial() throws ISOException, IOException {
        if (packagerFinancial == null) {
            Resource resource = new ClassPathResource("FinancialTransaction.xml");
            packagerFinancial = new GenericPackager();
            packagerFinancial.readFile(resource.getInputStream());
        }
        return packagerFinancial;
    }


    /**
     * Encoder untuk mengirimkan ISO8583 Message dengan menyertakan 2 byte message
     * length di depan message yang dikirimkan
     *
     * @return
     */
    @Bean("lengthFieldPrependerEncoder")
    public LengthFieldPrepender getLengthFieldPrepender() {
        // Menambahkan 2 byte message length pada ISOMessage yang dikirim ke server
        return new LengthFieldPrepender(2, Boolean.FALSE);
    }

    @Bean("lengthFieldBasedFrameDecoder")
    public LengthFieldBasedFrameDecoder getLengthFieldBasedFrameDecoder() {
//		return new SmartVistaDecoder(Integer.MAX_VALUE, 0, 2);
        return new SmartVistaDecoder(Integer.MAX_VALUE, 0, 2, 0, 2);
    }


}
