<?xml version="1.0" encoding="UTF-8"?>
<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <!-- Layout definition -->
    <fo:layout-master-set>
        <fo:simple-page-master master-name="A4" page-height="29.7cm" page-width="21cm" margin="2cm">
            <fo:region-body margin="1cm"/>
        </fo:simple-page-master>
    </fo:layout-master-set>

    <!-- Page sequence -->
    <fo:page-sequence master-reference="A4">
        <fo:flow flow-name="xsl-region-body">

            <!-- Title block -->
            <fo:block font-size="36pt" font-weight="bold" text-align="center" space-after="20pt" color="#2C3E50" font-family="Arial, sans-serif">
                INVOICE
            </fo:block>

            <!-- Hotel name -->
            <fo:block font-size="20pt" font-weight="bold" text-align="center" space-after="20pt" color="#3498DB" font-family="Arial, sans-serif">
                Ocean Pearl
            </fo:block>

            <!-- Customer name -->
            <fo:block font-size="16pt" font-weight="bold" space-after="10pt" color="#34495E" font-family="Arial, sans-serif">
                Customer: <xsl:value-of select="/InvoiceDto/name"/>
            </fo:block>

            <!-- Table for order details -->
            <fo:table table-layout="fixed" width="100%" border="1pt solid #BDC3C7" border-collapse="collapse">
                <fo:table-column column-width="10%"/>
                <fo:table-column column-width="40%"/>
                <fo:table-column column-width="20%"/>
                <fo:table-column column-width="15%"/>
                <fo:table-column column-width="15%"/>

                <fo:table-header>
                    <fo:table-row background-color="#2980B9" color="white">
                        <fo:table-cell padding="8pt" border="1pt solid #BDC3C7">
                            <fo:block font-weight="bold">ID</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="8pt" border="1pt solid #BDC3C7">
                            <fo:block font-weight="bold">Name</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="8pt" border="1pt solid #BDC3C7">
                            <fo:block font-weight="bold">Quantity</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="8pt" border="1pt solid #BDC3C7">
                            <fo:block font-weight="bold">Price</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="8pt" border="1pt solid #BDC3C7">
                            <fo:block font-weight="bold">Total Price</fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-header>

                <fo:table-body>
                    <xsl:for-each select="/InvoiceDto/orders/order">
                        <fo:table-row>
                            <fo:table-cell padding="8pt" border="1pt solid #BDC3C7">
                                <fo:block><xsl:value-of select="id"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="8pt" border="1pt solid #BDC3C7">
                                <fo:block><xsl:value-of select="name"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="8pt" border="1pt solid #BDC3C7">
                                <fo:block><xsl:value-of select="quantity"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="8pt" border="1pt solid #BDC3C7">
                                <fo:block>$<xsl:value-of select="price"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="8pt" border="1pt solid #BDC3C7">
                                <fo:block>$<xsl:value-of select="totalPrice"/></fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:for-each>
                </fo:table-body>
            </fo:table>

            <!-- Total amount before GST -->
            <fo:block font-size="16pt" font-weight="bold" space-before="20pt" color="#2C3E50" font-family="Arial, sans-serif">
                Total Amount (Before GST): $<xsl:value-of select="/InvoiceDto/totalAmt"/>
            </fo:block>

            <!-- GST Calculation -->
            <fo:block font-size="16pt" font-weight="bold" space-before="10pt" color="#E74C3C" font-family="Arial, sans-serif">
                GST (5%): $<xsl:value-of select="/InvoiceDto/totalAmt * 0.05"/>
            </fo:block>

            <!-- Total amount after GST -->
            <fo:block font-size="16pt" font-weight="bold" space-before="10pt" color="#27AE60" font-family="Arial, sans-serif">
                Total Amount (After GST): $<xsl:value-of select="/InvoiceDto/totalAmt + (/InvoiceDto/totalAmt * 0.05)"/>
            </fo:block>

        </fo:flow>
    </fo:page-sequence>
</fo:root>
