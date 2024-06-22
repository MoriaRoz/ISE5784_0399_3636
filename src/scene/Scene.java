package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * Represents a scene in 3D space.
 * name-name of the scene,
 * background- a background color
 * ambientLight- an ambient light source
 * geometries- a collection of geometries.
 */
public class Scene {

    public String name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight=AmbientLight.NONE;
    public Geometries geometries=new Geometries();

    /** Constructor
     * @param name- name of the scene */
    public Scene(String name) {
        this.name = name;
    }

    //Setters
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
