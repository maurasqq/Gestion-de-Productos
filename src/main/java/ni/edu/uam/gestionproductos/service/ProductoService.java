package ni.edu.uam.gestionproductos.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import ni.edu.uam.gestionproductos.dto.ProductoRequestDTO;
import ni.edu.uam.gestionproductos.entity.Categoria;
import ni.edu.uam.gestionproductos.entity.Etiqueta;
import ni.edu.uam.gestionproductos.entity.Producto;
import ni.edu.uam.gestionproductos.entity.Proveedor;
import ni.edu.uam.gestionproductos.repository.CategoriaRepository;
import ni.edu.uam.gestionproductos.repository.EtiquetaRepository;
import ni.edu.uam.gestionproductos.repository.ProductoRepository;
import ni.edu.uam.gestionproductos.repository.ProveedorRepository;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;
    private final EtiquetaRepository etiquetaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository,
            ProveedorRepository proveedorRepository, EtiquetaRepository etiquetaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
        this.etiquetaRepository = etiquetaRepository;
    }

    public List<Producto> listar() { return productoRepository.findAll(); }
    public Producto buscar(Integer id) { return productoRepository.findById(id).orElseThrow(); }
    public Producto buscarPorId(Integer id) { return buscar(id); }
    public List<Producto> listarPorCategoria(Integer categoriaId) { return productoRepository.findByCategoriaId(categoriaId); }

    public Producto guardar(ProductoRequestDTO dto) {
        return productoRepository.save(construir(new Producto(), dto));
    }

    public Producto actualizar(Integer id, ProductoRequestDTO dto) {
        return productoRepository.save(construir(buscar(id), dto));
    }

    public void eliminar(Integer id) { productoRepository.deleteById(id); }

    private Producto construir(Producto producto, ProductoRequestDTO dto) {
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setExistencia(dto.getExistencia());
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId()).orElseThrow();
        producto.setCategoria(categoria);
        producto.setProveedor(dto.getProveedorId() == null ? null : proveedorRepository.findById(dto.getProveedorId()).orElseThrow());
        producto.setEtiquetas(dto.getEtiquetaIds() == null ? new HashSet<>() : new HashSet<>(etiquetaRepository.findAllById(dto.getEtiquetaIds())));
        return producto;
    }

    public Producto asociarEtiquetas(Integer id, List<Integer> etiquetaIds) {
        Producto producto = buscar(id);
        producto.setEtiquetas(new HashSet<>(etiquetaRepository.findAllById(etiquetaIds)));
        return productoRepository.save(producto);
    }

    public Producto agregarEtiqueta(Integer productoId, Integer etiquetaId) {
        Producto producto = buscarPorId(productoId);
        Etiqueta etiqueta = etiquetaRepository.findById(etiquetaId).orElseThrow();
        producto.getEtiquetas().add(etiqueta);
        return productoRepository.save(producto);
    }
}
