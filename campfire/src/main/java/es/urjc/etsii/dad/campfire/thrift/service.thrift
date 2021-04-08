namespace cpp es.urjc.etsii.dad.campfire.thrift
namespace java es.urjc.etsii.dad.campfire.thrift

exception InvalidOperationException {
    1: i32 code,
    2: string description
}

service CrossPlatformService {

    string sendMessage(1:string message) throws (1:InvalidOperationException e)
    
}