package edu.upc.dsa.services;

import edu.upc.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/music", description = "Endpoint to MyMusic service")
@Path("/music")
public class MyMusicService {
    private  MyMusic mm;

    public MyMusicService (){
        mm = MyMusicImpl.getInstance();
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
            @ApiResponse(code = 201, message = "Successful", response = List.class, responseContainer = "List")
    })
    @Path("/listSingers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSingers() {
        List<Singer> list = mm.listSingers();
        return Response.status(201).entity(list).build()  ;
    }

    @POST
    @ApiOperation(value = "add a playlist to a user", notes = "Response: OK-> playlist, User not found -> user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = IdNameTO.class),
            @ApiResponse(code = 404, message = "User not found", response = IdNameTO.class)
    })
    @Path("/addPlaylist/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(String userId, IdNameTO playlist) {
        try{
            mm.addPlaylist(playlist.id, playlist.name, userId);
            return Response.status(201).entity(playlist).build()  ;
        }
        catch(UserNotFoundException e) {
            IdNameTO res = new IdNameTO();
            res.id = userId;
            res.name = null;
            return Response.status(404).entity(res).build()  ;
        }
    }

    @POST
    @ApiOperation(value = "add a track to a playlist", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = IdNameTO.class),
            @ApiResponse(code = 404, message = "User not found", response = IdNameTO.class),
            @ApiResponse(code = 404, message = "Playlist not found", response = IdNameTO.class)
    })
    @Path("/addTrack/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(String userId, String playlistId, Track track) {
        try{
            mm.addTrack(track.getId(), track.getTitle(), track.getSingerId(), track.getAlbum(), track.getLength(), playlistId, userId);
            return Response.status(201).entity(track).build()  ;
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

    @GET
    @ApiOperation(value = "Get all tracks from a playlist", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = List.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "User not found", response = IdNameTO.class),
            @ApiResponse(code = 404, message = "Playlist not found", response = IdNameTO.class)
    })
    @Path("/getTracks/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTracks(String userId, String playlistId) {
        try{
            return Response.status(201).entity(mm.listTracks(userId, playlistId)).build()  ;
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
