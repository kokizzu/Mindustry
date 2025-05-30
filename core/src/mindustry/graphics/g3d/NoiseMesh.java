package mindustry.graphics.g3d;

import arc.graphics.*;
import arc.math.geom.*;
import arc.util.noise.*;
import mindustry.graphics.*;
import mindustry.type.*;

public class NoiseMesh extends HexMesh{

    public NoiseMesh(Planet planet, int seed, int divisions, Color color, float radius, int octaves, float persistence, float scale, float mag){
        this.planet = planet;
        this.shader = Shaders.planet;
        this.mesh = MeshBuilder.buildHex(new HexMesher(){
            @Override
            public float getHeight(Vec3 position){
                return Simplex.noise3d(7 + seed, octaves, persistence, scale, 5f + position.x, 5f + position.y, 5f + position.z) * mag;
            }

            @Override
            public Color getColor(Vec3 position){
                return color;
            }
        }, divisions, radius, 0.2f);
    }

    /** Two-color variant. */
    public NoiseMesh(Planet planet, int seed, int divisions, float radius, int octaves, float persistence, float scale, float mag, Color color1, Color color2, int coct, float cper, float cscl, float cthresh){
        this.planet = planet;
        this.shader = Shaders.planet;
        this.mesh = MeshBuilder.buildHex(new HexMesher(){
            @Override
            public float getHeight(Vec3 position){
                return Simplex.noise3d(7 + seed, octaves, persistence, scale, 5f + position.x, 5f + position.y, 5f + position.z) * mag;
            }

            @Override
            public Color getColor(Vec3 position){
                return Simplex.noise3d(8 + seed, coct, cper, cscl, 5f + position.x, 5f + position.y, 5f + position.z) > cthresh ? color2 : color1;
            }
        }, divisions, radius, 0.2f);
    }
}
