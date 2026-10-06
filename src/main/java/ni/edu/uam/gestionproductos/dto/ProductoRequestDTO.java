package ni.edu.uam.gestionproductos.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductoRequestDTO {
    private String codigo;
    private String nombre;
    private String descripcion;
    private BigDecimal precioVenta;
    private int existencia;
    private Integer categoriaId;
    private Integer proveedorId;
    private List<Integer> etiquetaIds = new ArrayList<>();

    public ProductoRequestDTO() { }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }
    public int getExistencia() { return existencia; }
    public void setExistencia(int existencia) { this.existencia = existencia; }
    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }
    public Integer getProveedorId() { return proveedorId; }
    public void setProveedorId(Integer proveedorId) { this.proveedorId = proveedorId; }
    public List<Integer> getEtiquetaIds() { return etiquetaIds; }
    public void setEtiquetaIds(List<Integer> etiquetaIds) { this.etiquetaIds = etiquetaIds; }
}
