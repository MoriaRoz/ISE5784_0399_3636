package primitives;

/**
 * Material class represents the material of a 3D object.
 * It contains the diffusion attenuation coefficient, the specular attenuation coefficient and the shininess factor.
 */
public class Material {
    public Double3 kR = Double3.ZERO; // Reflection attenuation coefficient
    public Double3 kT = Double3.ZERO; // Reflection attenuation coefficient
    public Double3 kD = Double3.ZERO; // Diffusion attenuation coefficient
    public Double3 kS = Double3.ZERO; // Specular attenuation coefficient
    public int nShininess = 0; // Shininess factor

    /**
     * Set the diffusion attenuation coefficient
     *
     * @param kR the value to set
     * @return this Material object
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Set the diffusion attenuation coefficient to the same value for all 3 numbers
     *
     * @param d the value to set
     * @return this Material object
     */
    public Material setkR(double d) {
        this.kR = new Double3(d);
        return this;
    }

    /**
     * Set the diffusion attenuation coefficient
     *
     * @param kT the value to set
     * @return this Material object
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Set the diffusion attenuation coefficient to the same value for all 3 numbers
     *
     * @param d the value to set
     * @return this Material object
     */
    public Material setkT(double d) {
        this.kT = new Double3(d);
        return this;
    }

    /**
     * Set the diffusion attenuation coefficient
     *
     * @param kD the value to set
     * @return this Material object
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Set the diffusion attenuation coefficient to the same value for all 3 numbers
     *
     * @param d the value to set
     * @return this Material object
     */
    public Material setkD(double d) {
        this.kD = new Double3(d);
        return this;
    }

    /**
     * Set the specular attenuation coefficient
     *
     * @param kS the value to set
     * @return this Material object
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Set the specular attenuation coefficient to the same value for all 3 numbers
     *
     * @param d the value to set
     * @return this Material object
     */
    public Material setkS(double d) {
        this.kS = new Double3(d);
        return this;
    }

    /**
     * Set the shininess factor
     *
     * @param nShininess the value to set
     * @return this Material object
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
