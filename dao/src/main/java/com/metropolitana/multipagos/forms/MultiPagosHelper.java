package com.metropolitana.multipagos.forms;

import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.commons.lang.math.NumberUtils;

public class MultiPagosHelper {

    /**
     * @deprecated Use mejor NumberUtils.INTEGER_ZERO.
     */
    public static final Integer INTEGER_CERO = NumberUtils.INTEGER_ZERO;

    /**
     * Calcula el monto del porcentaje sobre el valor dado.
     *
     * @param valor
     *            Valor sobre el cual se calcula el porcentaje
     * @param porcentaje
     *            Valor que indica el porcentaje a calcular
     * @return BigDecimal Monto del porcentaje sobre el valor dado
     */
    public static BigDecimal calcularMontoPorcentajeInt(BigDecimal valor, Integer porcentaje) {
        BigDecimal monto = BigDecimal.ZERO;
        if (porcentaje != null && porcentaje.intValue() > 0 && valor != null && valor.signum() > 0) {
            monto = valor.multiply(new BigDecimal(porcentaje.intValue()).movePointLeft(2)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return monto;
    }

    public static BigDecimal calcularMontoPorcentaje(BigDecimal valor, BigDecimal porcentaje) {
        BigDecimal monto = BigDecimal.ZERO;
            if (porcentaje != null && porcentaje.signum() > 0 && valor != null && valor.signum() > 0) {
                monto = valor.multiply(porcentaje.movePointLeft(2)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        return monto;
    }

    public static BigDecimal calcularMontoPorcentaje4Decimales(BigDecimal valor, BigDecimal porcentaje) {
        BigDecimal monto = BigDecimal.ZERO;
            if (porcentaje != null && porcentaje.signum() > 0 && valor != null && valor.signum() > 0) {
                monto = valor.multiply(porcentaje.movePointLeft(2)).setScale(4, BigDecimal.ROUND_HALF_UP);
            }
        return monto;
    }

    /**
     * Calcula el subtotal de un producto, el descuento es aplicado al resultado.
     *
     * @param cantidad
     *            Cantidad de producto
     * @param precioUnitario
     *            Precio Unitario del producto
     * @param descuento
     *            Descuento aplicable
     * @return Subtotal del producto aplicado el descuento
     */
    public static BigDecimal calcularSubTotalFila(BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal descuento) {
    	BigDecimal desc = calcularMontoPorcentaje(precioUnitario, descuento);
        return cantidad.multiply(precioUnitario.subtract(desc));
    }

    /**
     * Calcula el subtotal de un producto, el descuento es aplicado al
     * resultado, el resultado es convertido usando la tasa de cambio pasa en
     * los parámetros.
     *
     * @param cantidad
     *            Cantidad de producto
     * @param precioUnitario
     *            Precio unitario del producto
     * @param descuento
     *            Descuento aplicable
     * @param tasaCambio
     *            Tasa de cambio para convertir el precio unitario
     * @return SubTotal de un producto
     */
    public static BigDecimal calcularSubTotalFila(BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal descuento,
            BigDecimal tasaCambio) {
        BigDecimal pre = precioUnitario.subtract(calcularMontoPorcentaje(precioUnitario, descuento));
        return pre.multiply(tasaCambio).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(cantidad).setScale(2,
                BigDecimal.ROUND_HALF_UP);
    }
    /**
     * Calcula el subtotal de un producto, el descuento es aplicado al resultado.
     *
     * @param cantidad
     * 			Cantidad de producto
     * @param precioUnitario
     * 			Precio unitario del producto
     * @param descuento
     * 			Descuento aplicable
     * @return SubTotal de un producto.
     */
    public static BigDecimal calcularSubTotalFilaFactura(BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal descuento) {
        BigDecimal pre = precioUnitario.subtract(calcularMontoPorcentaje4Decimales(precioUnitario, descuento));
        return pre.multiply(cantidad).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Calcula el monto del descuento de un producto.
     *
     * @param cantidad
     *            Cantidad del producto
     * @param precioUnitario
     *            Precio Unitario del producto
     * @param descuento
     *            Descuento Aplicable
     * @return Monto del descuento de un producto
     */

    public static BigDecimal calcularDescuentoFila(BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal descuento) {
        BigDecimal pre = precioUnitario.subtract(calcularMontoPorcentaje(precioUnitario, descuento));
        return pre.multiply(cantidad).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Calcula el monto del descuento en córdobas.
     *
     * @param cantidad
     *            Cantidad del producto
     * @param precioUnitario
     *            Precio Unitario del producto
     * @param descuento
     *            Descuento aplicable
     * @param tasaCambio
     *            Tasa de cambio para la conversión
     * @return Monto del Descuento en Córdobas
     */
    public static BigDecimal calcularDescuentoFila(BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal descuento,
            BigDecimal tasaCambio) {
        BigDecimal pre = calcularMontoPorcentaje(precioUnitario, descuento);
        return pre.multiply(tasaCambio).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(cantidad).setScale(2,
                BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Calcular el Porcentaje del impuesto aplicado a la factura. El porcentaje
     * del impuesto es expresado como una cantidad entera.
     *
     * @param subTotal
     *            Sub Total de la factura, esta ya debe incluir el descuento
     * @param montoImp
     *            Monto del impuesto de la factura
     * @return Valor Entero que representa el porcentaje del impuesto aplicado a
     *         una factura
     * @throws Exception
     */
    public static Integer calcularPorcentajeImpuesto(BigDecimal subTotal, BigDecimal montoImp) throws Exception {
        return montoImp.divide(subTotal, 2).movePointRight(2).intValue();
    }

    /**
     * Calcula el subtotal con descuento en las facturas de venta.
     * @param subTotal
     *          Monto correspondiente al subtotal en la factura de venta.
     * @param descuento
     *          Descuento en la factura de venta.
     * @return
     * @throws Exception
     */
    public static BigDecimal calcularSubTotalDescuento(BigDecimal subTotal, BigDecimal descuento) throws Exception {
        return subTotal.subtract(calcularMontoPorcentaje(subTotal, descuento));
    }

    /**
     *
     * @param carga
     * @param devolucion
     * @param precioUnitario
     * @return
     */
    public static BigDecimal calcularTotalVenta(BigDecimal carga, BigDecimal devolucion, BigDecimal precioUnitario) {
        if (devolucion != null) {
        	BigDecimal diferencia = carga.subtract(devolucion).setScale(2, BigDecimal.ROUND_HALF_UP);
            return diferencia.multiply(precioUnitario).setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            return null;
        }
    }
}
