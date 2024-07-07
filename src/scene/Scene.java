package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a scene in 3D space.
 * name-name of the scene,
 * background- a background color
 * ambientLight- an ambient light source
 * geometries- a collection of geometries.
 */
public class Scene {

    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructor
     *
     * @param name- name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    //Setters

    /**
     * set the background color to the scene
     *
     * @param background- the background color to add
     * @return the scene
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * set the ambient light source to the scene
     *
     * @param ambientLight- the ambient light source to add
     * @return the scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * set the geometries to the scene
     *
     * @param geometries- the geometries to add
     * @return the scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * set the light source to the scene
     *
     * @param lights- the light source to add
     * @return the scene
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
