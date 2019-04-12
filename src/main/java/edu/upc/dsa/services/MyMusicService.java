package edu.upc.dsa.services;

import edu.upc.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/music", description = "Endpoint to MyMusic service")
@Path("/music")
public class MyMusicService {
    private  MyMusic mm;

    public MyMusicService (){
        mm = MyMusicImpl.getInstance();
        /*mm.addUser("u1", "Anton");
        try{
        mm.addPlaylist("p1", "Playlist1", "u1");}
        catch(Exception e){}*/
    }

    @POST
    @ApiOperation(value = "add a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = IdNameTO.class)
    })
    @Path("/addUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(IdNameTO user) {
        mm.addUser(user.id, user.name);
        return Response.status(201).entity(user).build()  ;
    }

    @POST
    @ApiOperation(value = "add a singer", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = IdNameTO.class)
    })
    @Path("/addSinger")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSinger(IdNameTO singer) {
        mm.addSinger(singer.id, singer.name);
        return Response.status(201).entity(singer).build();
    }

    @GET
    @ApiOperation(value = "Get singer list", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Singer.class, responseContainer = "List")
    })
    @Path("/listSingers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSingers() {
        List<Singer> list = mm.listSingers();
        GenericEntity<List<Singer>> entity = new GenericEntity<List<Singer>>(list) {};

        return Response.status(201).entity(entity).build()  ;
    }

    @POST
    @ApiOperation(value = "add a playlist to a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PlaylistTO.class),
            @ApiResponse(code = 404, message = "User not found", response = PlaylistTO.class)
    })
    @Path("/addPlaylist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistTO playlist) {
        try{
            mm.addPlaylist(playlist.playlistId, playlist.name, playlist.userId);
            return Response.status(201).entity(playlist).build()  ;
        }
        catch(UserNotFoundException e) {
            return Response.status(404).entity(playlist).build()  ;
        }
    }

    @POST
    @ApiOperation(value = "add a track to a playlist", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = TrackTO.class),
            @ApiResponse(code = 404, message = "User not found", response = IdNameTO.class),
            @ApiResponse(code = 404, message = "Playlist not found", response = IdNameTO.class)
    })
    @Path("/addTrack")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrack(TrackTO track) {
        try{
            mm.addTrack(track.track.getId(), track.track.getTitle(), track.track.getSingerId(), track.track.getAlbum(), track.track.getLength(), track.playlistId, track.userId);
            return Response.status(201).entity(track).build()  ;
        }
        catch (UserNotFoundException e) {
            IdNameTO res = new IdNameTO();
            res.id = track.userId;
            res.name = null;
            return Response.status(404).entity(res).build()  ;
        }
        catch (PlaylistNotFoundException e) {
            IdNameTO res = new IdNameTO();
            res.id = track.playlistId;
            res.name = null;
            return Response.status(404).entity(res).build()  ;
        }
    }

    @GET
    @ApiOperation(value = "Get all tracks from a playlist", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Track.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "User not found", response = IdNameTO.class),
            @ApiResponse(code = 404, message = "Playlist not found", response = IdNameTO.class)
    })
    @Path("/getTracks/{userId}/{playlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@PathParam("userId") String userId, @PathParam("playlistId") String playlistId) {
        try{
            List<Track> tracks = mm.listTracks(userId, playlistId);
            GenericEntity<List<Track>> entity = new GenericEntity<List<Track>>(tracks) {};

            return Response.status(201).entity(entity).build()  ;
        }
        catch (UserNotFoundException e) {
            IdNameTO res = new IdNameTO();
            res.id = userId;
            res.name = null;
            return Response.status(404).entity(res).build()  ;
        }
        catch (PlaylistNotFoundException e) {
            IdNameTO res = new IdNameTO();
            res.id = playlistId;
            res.name = null;
            return Response.status(404).entity(res).build()  ;
        }
    }


}
