/*
 * Archivo: Comparador.java
 * Autor: Rafael Marquez
 * Creado el 10-06-2010
 *
 */
package com.metropolitana.multipagos.forms;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

/**
 * Comparar arreglos de objetos usando uno o más valores del arreglo
 *
 * @author rmarquez
 *
 */
public class Comparador {

    /**
     * Método que se encarga de realizar la comparación de dos objetos. Por el
     * momento se hace la comparación de objetos de tipo Integer, String, Date,
     * BigDecimal
     *
     * @param o1
     *            Primer elemento a comparar
     * @param o2
     *            Segundo elemento a comparar
     * @return ver compareTo
     */
    private int comparar(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            if (Integer.class.isInstance(o1) && Integer.class.isInstance(o2)) {
                return ((Integer) o1).compareTo((Integer) o2);
            } else if (String.class.isInstance(o1) && String.class.isInstance(o2)) {
                return ((String) o1).compareTo((String) o2);
            } else if (Date.class.isInstance(o1) && Date.class.isInstance(o2)) {
                return ((Date) o1).compareTo((Date) o2);
            } else if (BigDecimal.class.isInstance(o1) && BigDecimal.class.isInstance(o2)) {
                return ((BigDecimal) o1).compareTo((BigDecimal) o2);
            }
            throw new NullPointerException(
                    "comparar: Los Objetos a comprar deben ser Date, Integer, String o BigDecimal");
        } else {
            throw new NullPointerException("comparar.comparador: Los Objetos a comprar son nulos.");
        }
    }

    /**
     * Comparador por defecto. Retorna el comparador que usa el primer elemento
     * del arreglo
     *
     * @return Comparator
     */
    public Comparator getComparador() {
        // Usar el primer elemento del arreglo para comparar
        return new ComparadorUno(0);
    }

    /**
     * Retorna un comparador. El comparador puede usar el elemento indicado en
     * los parámetros para compara los elemento de dos arreglos
     *
     * @param indice
     *            Posición del elemento dentro del arreglo usado para comparar
     * @return Comparator
     */
    public Comparator getComparador(int indice) {

        if (indice >= 0) {
            return new ComparadorUno(indice);
        }
        throw new NullPointerException("No se pudo definir un comparador. El indice debe ser mayor o igual que cero.");
    }

    /**
     * Retorna un comparador. El comparador pueden usar dos ó más elementos para
     * comparar dos arreglos
     *
     * @param indiceInicial
     *            Posición del primer elemento del arreglo para realizar la
     *            comparación
     * @param indiceFinal
     *            Posición del último elemento del arreglo para realizar la
     *            comparación
     * @return Comparator
     */
    public Comparator getComparador(int indiceInicial, int indiceFinal) {

        if (indiceInicial >= 0 && indiceFinal >= 0) {
            if (indiceInicial == indiceFinal) {
                // La llamar a este método los indices deben ser diferentes
                // en todo caso se debe llamar al comparador por defecto o
                // al comparador con un indice
                throw new NullPointerException("Las posiciones de los elementos del arreglo deben ser diferentes.");
            } else if (indiceInicial < indiceFinal) {
                if ((indiceInicial + 1) == indiceFinal) {
                    return new ComparadorDos(indiceInicial, indiceFinal);
                }
                return new ComparadorIzqDer(indiceInicial, indiceFinal);
            } else if (indiceInicial > indiceFinal) {
                if ((indiceInicial - 1) == indiceFinal) {
                    return new ComparadorDos(indiceInicial, indiceFinal);
                }
                return new ComparadorDerIzq(indiceInicial, indiceFinal);
            }
        }
        throw new NullPointerException("No se pudo definir un comparador. El indice debe ser mayor o igual que cero.");
    }

    /**
     * Retorna un comparador. El comparador puede usar varios elementos del
     * arreglo para realizar la comparación. Las posiciones de los elementos a
     * comparar vienen en un arreglo.
     *
     * @param indices
     *            Arreglo de enteros donde vienen las posiciones de los
     *            elementos a comparar
     * @return Comparator
     */
    public Comparator getComparador(int[] indices) {

        if (indices != null && indices.length > 0) {
            return new ComparadorArray(indices);
        }
        throw new NullPointerException(
                "No se pudo definir un comparador. La cantidad de elementos a comparar deber ser mayor que cero.");
    }

    /**
     * Comparar con un elemento del arreglo
     *
     * @author rmarquez
     *
     */
    private class ComparadorUno implements Comparator {
        private int indice = 0;

        public ComparadorUno(int indice) {
            this.indice = indice;
        }

        public int compare(Object o1, Object o2) {
            Object[] m1 = (Object[]) o1;
            Object[] m2 = (Object[]) o2;
            return comparar(m1[indice], m2[indice]);
        }
    }

    /**
     * Comparar con dos elementos del arreglo
     *
     * @author rmarquez
     *
     */
    private class ComparadorDos implements Comparator {
        private int indiceInicial = 0;

        private int indiceFinal = 0;

        public ComparadorDos(int indiceInicial, int indiceFinal) {
            this.indiceInicial = indiceInicial;
            this.indiceFinal = indiceFinal;
        }

        public int compare(Object o1, Object o2) {
            Object[] m1 = (Object[]) o1;
            Object[] m2 = (Object[]) o2;

            int index1 = comparar(m1[indiceInicial], m2[indiceInicial]);
            int index2 = comparar(m1[indiceFinal], m2[indiceFinal]);

            if (index1 != 0) {
                return index1;
            } else if (index2 != 0) {
                return index2;
            } else {
                return 0;
            }
        }
    }

    /**
     * Comparar con varios elementos del arreglo de Izquierda a Derecha
     *
     * @author rmarquez
     *
     */
    private class ComparadorIzqDer implements Comparator {
        private int indiceInicial = 0;

        private int indiceFinal = 0;

        public ComparadorIzqDer(int indiceInicial, int indiceFinal) {
            this.indiceInicial = indiceInicial;
            this.indiceFinal = indiceFinal;
        }

        public int compare(Object o1, Object o2) {
            Object[] m1 = (Object[]) o1;
            Object[] m2 = (Object[]) o2;

            for (int i = indiceInicial; i <= indiceFinal; i++) {
                int comp = comparar(m1[i], m2[i]);
                if (comp != 0) {
                    return comp;
                }
            }
            return 0;
        }
    }

    /**
     * Comparar con varios elementos del arreglo de Derecha a Izquierda
     *
     * @author rmarquez
     *
     */
    private class ComparadorDerIzq implements Comparator {
        private int indiceInicial = 0;

        private int indiceFinal = 0;

        public ComparadorDerIzq(int indiceInicial, int indiceFinal) {
            this.indiceInicial = indiceInicial;
            this.indiceFinal = indiceFinal;
        }

        public int compare(Object o1, Object o2) {
            Object[] m1 = (Object[]) o1;
            Object[] m2 = (Object[]) o2;

            for (int i = indiceInicial; i >= indiceFinal; i--) {
                int comp = comparar(m1[i], m2[i]);
                if (comp != 0) {
                    return comp;
                }
            }
            return 0;
        }
    }

    /**
     * Comparar mediante varios elementos del arreglo, donde las posiciones de
     * los elementos vienen en un arreglo de enteros
     *
     * @author rmarquez
     *
     */
    private class ComparadorArray implements Comparator {
        private int[] indices;

        public ComparadorArray(int[] lst) {
            if (lst.length > 0) {
                indices = lst;
            } else {
                throw new NullPointerException("Debe indicar cuales son las posiciones de los elementos a comparar.");
            }
        }

        public int compare(Object o1, Object o2) {
            Object[] m1 = (Object[]) o1;
            Object[] m2 = (Object[]) o2;

            if (indices != null && indices.length > 0) {
                for (int i = 0; i < indices.length; i++) {
                    if (indices[i] >= 0) {
                        int comp = comparar(m1[indices[i]], m2[indices[i]]);
                        if (comp != 0) {
                            return comp;
                        }
                    } else {
                        throw new NullPointerException("Elementos a comparar desconocidos.");
                    }
                }
                return 0;
            }
            throw new NullPointerException("Elementos a comparar desconocidos.");
        }
    }

}